package com.felix;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisPubSubTest {

    private static final Logger log = LoggerFactory.getLogger(RedisPubSubTest.class);

    private RedisClient redisClient;
    StatefulRedisPubSubConnection<String, String> connection;

    private static final String TEST_CHANNEL = "channel-test";

    @Before
    public void init() {
        RedisURI redisURI = RedisURI.Builder
                .redis("192.168.197.101", 6379)
                .withPassword("redis123")
                .withDatabase(0)
                .build();
        redisClient = RedisClient.create(redisURI);

    }

    /**
     * publisher给在线的subscriber广播消息, 无消费者组概念, 无在线的subscriber直接丢弃消息
     * <p>
     * 好了，现在我们总结一下 Pub/Sub 的优缺点：
     * <p>
     * 1. 支持发布 / 订阅，支持多组生产者、消费者处理消息
     * 2. 消费者下线，数据会丢失
     * 3. 不支持数据持久化，Redis 宕机，数据也会丢失
     * 4. 消息堆积，缓冲区溢出，消费者会被强制踢下线，数据也会丢失
     */
    @Test
    public void publish() {
        StatefulRedisPubSubConnection listen = listen();

        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub();
        RedisPubSubAsyncCommands<String, String> async = connection.async();

        async.publish(TEST_CHANNEL, "hello");
        async.publish(TEST_CHANNEL, "world");

        System.out.println("==================================>");
    }


    public StatefulRedisPubSubConnection listen() {

        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub();

        connection.addListener(new RedisPubSubListener<String, String>() {
            @Override
            public void message(String channel, String message) {
                log.info("====> redis [channel subscribe] channel {}, message {}", channel, message);
            }

            @Override
            public void message(String pattern, String channel, String message) {
                log.info("====> redis [pattern subscribe] pattern {}, channel {}, message {}", pattern, channel, message);

//                throw new RuntimeException("====> 未成功消费 ===========");
            }

            @Override
            public void subscribed(String channel, long count) {
                log.info("====> redis [count subscribe], channel {} count {}", channel, count);
            }

            @Override
            public void psubscribed(String pattern, long count) {
                log.info("====> redis [pattern subscribe], pattern {} count {}", pattern, count);
            }

            @Override
            public void unsubscribed(String channel, long count) {
                log.info("====> redis [count unsubscribe], channel {} count {}", channel, count);
            }

            @Override
            public void punsubscribed(String pattern, long count) {
                log.info("====> redis [pattern unsubscribe], pattern {} count {}", pattern, count);
            }
        });

        connection.async().subscribe(TEST_CHANNEL);

        return connection;
    }


}
