package com.felix;


import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisOpsTest {

    private static final Logger log = LoggerFactory.getLogger(RedisOpsTest.class);

    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;
    private RedisCommands<String, String> commands;

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

    public void redisDelKey(String keyPattern) {
        List<String> keys = commands.keys(keyPattern);
        for (String key : keys) {
            //NONE("none"), STRING("string"), LIST("list"), SET("set"), ZSET("zset"), HASH("hash"),
            //	/**
            //	 * @since 2.2
            //	 */
            //	STREAM("stream")
            String type = commands.type(key);
            Long del = commands.del(key);
            log.info("====> redis contains key {}, type {}, deleted {}", key, type, del);
        }
    }

    @Test
    public void redis_string() {
        String key = "first code";
        commands.set(key, "hello world!", SetArgs.Builder.ex(10));

        String value = commands.get(key);
        Long ttl = commands.ttl(key);
        log.info("====> redis [string] get, key {}, value {}, ttl {}", key, value, ttl);

        this.redisDelKey(key);
    }

    @Test
    public void redis_list() {
        String key = "tasks";
        commands.lpush(key, "firstTask");
        commands.lpush(key, "secondTask", "thirdTask");

        String currentTask = commands.rpop(key);
        log.info("====> redis [list] rpop, current task: {}", currentTask);
        List<String> rangeTasks = commands.lrange(key, 0, 1);
        log.info("====> redis [list] lrange, tasks: {}", rangeTasks);

        this.redisDelKey(key);
    }

    @Test
    public void redis_set() {
        String key = "pets";
        commands.sadd(key, "dog");
        commands.sadd(key, "cat");
        commands.sadd(key, "cat");

        Set<String> pets = commands.smembers(key);
        log.info("====> redis [set] get, key {}, value {}", key, pets);
        Boolean isMember = commands.sismember(key, "dog");
        log.info("====> redis [set] get, key {}, contains 'dog' {}", key, isMember);

        this.redisDelKey(key);
    }

    @Test
    public void redis_hash() {
        String key = "recordName";
        commands.hset(key, "FirstName", "John");
        commands.hset(key, "LastName", "Smith");

        String firstName = commands.hget(key, "FirstName");
        log.info("====> redis [hash] hget, key {}, field {}, value {}", key, "FirstName", firstName);
        Map<String, String> map = commands.hgetall(key);
        log.info("====> redis [hash] hgetall, key {}, value {}", key, map);

        this.redisDelKey(key);
    }

    @Test
    public void redis_zset() {
        String key = "sortedset";
        commands.zadd(key, 1, "one");
        commands.zadd(key, 4, "zero");
        commands.zadd(key, 3, "three");

        List<String> zrange = commands.zrange(key, 0, 3);
        log.info("====> redis [zset] zrange, key {}, value {}", key, zrange);
        List<String> zrevrange = commands.zrevrange(key, 0, 3);
        log.info("====> redis [zset] zrevrange, key {}, value {}", key, zrange);

        this.redisDelKey(key);
    }

    /**
     * MULTI 会使后续命令操作进入队列
     * EXEC 会把队列中的命令按顺序执行
     */
    @Test
    public void redis_transaction() {
        commands.multi();
        commands.set("key1", "value1");
        commands.set("key2", "value2");
        commands.set("key3", "value3");

        commands.get("key1");
        commands.get("key2");
        commands.get("key3");

        TransactionResult execResult = commands.exec();
        Object o1 = execResult.get(0);
        Object o2 = execResult.get(1);
        Object o3 = execResult.get(2);
        Object o4 = execResult.get(3);
        Object o5 = execResult.get(4);
        Object o6 = execResult.get(5);

        log.info("====> redis [multi] get, result {} {} {} {} {} {}", o1, o2, o3, o4, o5, o6);
    }
}
