package com.felix;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * BF.RESERVE bf_order_id 0.01 1000 EXPANSION 2
 * <p>
 * BF.ADD bf_order_id 123456789
 * <p>
 * BF.EXISTS bf_order_id 123456789
 */
public class RedisBloomTest {

    private static final Logger log = LoggerFactory.getLogger(RedisBloomTest.class);


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
     * public enum Command implements ProtocolCommand {
     * RESERVE("BF.RESERVE"),
     * ADD("BF.ADD"),
     * MADD("BF.MADD"),
     * EXISTS("BF.EXISTS"),
     * MEXISTS("BF.MEXISTS"),
     * INSERT("BF.INSERT"),
     * INFO("BF.INFO");
     * <p>
     * private final byte[] raw;
     * <p>
     * Command(String alt) {
     * raw = SafeEncoder.encode(alt);
     * }
     * <p>
     * public byte[] getRaw() {
     * return raw;
     * }
     * }
     */
    @Test
    public void jedis_bloom_test() {
        //given
        String bfKey = "bf_order_id";
        //when
        Jedis jedis = jedisPool.getResource();
        Object o = jedis.sendCommand(() -> "BF.EXISTS".getBytes(StandardCharsets.UTF_8), bfKey, "123456789");
        //then
        System.out.println(o);
    }




}
