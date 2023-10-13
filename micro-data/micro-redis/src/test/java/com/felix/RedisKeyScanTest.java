package com.felix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.time.Duration;

/**
 * @author felix
 * @desc some desc
 */
public class RedisKeyScanTest {


    private JedisPool jedisPool;

    @Before
    public void init() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(poolConfig, "192.168.159.111", 6379, 500, "redis123", 0);
    }

    @After
    public void destroy() {
        jedisPool.destroy();
    }

    /**
     * SCAN cursor [MATCH pattern] [COUNT count] [TYPE type]
     * cursor - 游标。
     * pattern - 匹配的模式。
     * count - 可选，用于指定每次迭代返回的 key 的数量，默认值为 10 。
     */
    @Test
    public void run_scan_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();

        //when
        ScanResult<String> scanResult = jedis.scan("0", new ScanParams().match("b*").count(5));

        //then
        System.out.println("cursor: " + scanResult.getCursor());
        System.out.println("keys:");
        System.out.println(String.join("\n", scanResult.getResult()));
    }


}
