package com.felix;

import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RedisStreamTest {

    private static final Logger log = LoggerFactory.getLogger(RedisStreamTest.class);

    private RedisClient redisClient;
    StatefulRedisConnection<String, String> connection;
    RedisCommands<String, String> commands;


    @Before
    public void init() {
        RedisURI redisURI = RedisURI.Builder
                .redis("192.168.197.101", 6379)
                .withPassword("redis123")
                .withDatabase(0)
                .build();
        redisClient = RedisClient.create(redisURI);
        connection = redisClient.connect();
        commands = connection.sync();
    }

    /**
     * xadd some-stream * k1 v1 k2 v2
     */
    @Test
    public void produce_message() {
        String s1 = commands.xadd("some-stream", new XAddArgs().id("*"), "name", "felix", "age", "18");
        System.out.println(s1);
        String s2 = commands.xadd("some-stream", new XAddArgs().id("*"), "name", "linus", "age", "30");
        System.out.println(s2);
        String s3 = commands.xadd("some-stream", new XAddArgs().id("*"), "name", "celia", "age", "16");
        System.out.println(s3);
    }

    /**
     * xread streams some-stream 0-0
     */
    @Test
    public void consume_message_from_begin() {
        List<StreamMessage<String, String>> messages = commands.xread(new XReadArgs(), XReadArgs.StreamOffset.from("some-stream", "0-0"));
        for (StreamMessage<String, String> message : messages) {
            String id = message.getId();
            Map<String, String> body = message.getBody();
            log.info("redis [stream] xread, id {}, message {}", id, body);
        }
    }

    /**
     * xread BLOCK 1000 streams some-stream $
     */
    @Test
    public void consume_message_from_latest() {
        List<StreamMessage<String, String>> messages = commands.xread(new XReadArgs().block(1000), XReadArgs.StreamOffset.from("some-stream", "$"));
        for (StreamMessage<String, String> message : messages) {
            String id = message.getId();
            Map<String, String> body = message.getBody();
            log.info("redis [stream] xread, id {}, message {}", id, body);
        }
    }

    /**
     * XGROUP CREATE some-stream g1 0 MKSTREAM
     *
     * XREADGROUP GROUP g1 c1 count 3 STREAMS some-stream 0-0
     */
    @Test
    public void consume_message_by_group() {
        List<StreamMessage<String, String>> messages = commands.xreadgroup(Consumer.from("g1", "c1")
                , XReadArgs.StreamOffset.from("some-stream", "0-0"));
        for (StreamMessage<String, String> message : messages) {
            String id = message.getId();
            Map<String, String> body = message.getBody();
            log.info("redis [stream] xread, id {}, message {}", id, body);
        }
    }

    /**
     * XGROUP CREATE some-stream g2 0 MKSTREAM
     *
     * XREADGROUP GROUP g1 c1 count 3 STREAMS some-stream 0-0
     *
     *
     */
    @Test
    public void consume_message_by_group_ack() {
        List<StreamMessage<String, String>> messages = commands.xreadgroup(Consumer.from("g1", "c1")
                , XReadArgs.StreamOffset.from("some-stream", "0-0"));
        for (StreamMessage<String, String> message : messages) {
            String id = message.getId();
            Map<String, String> body = message.getBody();
            Long ack = commands.xack("some-stream", "g1", id);
            log.info("redis [stream] xread, id {}, message {}, ack {}", id, body, ack);
        }
    }

}
