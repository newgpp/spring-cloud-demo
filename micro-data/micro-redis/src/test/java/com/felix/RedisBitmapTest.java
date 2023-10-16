package com.felix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

/**
 * @author felix
 * @desc some desc
 */
public class RedisBitmapTest {

    private JedisPool jedisPool;
    private BinaryJedis binaryJedis;

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
        binaryJedis = new BinaryJedis("192.168.159.111", 6379);
        binaryJedis.auth("redis123");
        binaryJedis.select(0);
    }

    @After
    public void destroy() {
        jedisPool.destroy();
    }

    /**
     * 用户每个月签到情况
     * uid:sign:{userId}:{yyyyMM}
     *
     * SETBIT key offset value
     *
     */
    @Test
    public void bit_set_get_should_success() {

        //given
        Jedis jedis = jedisPool.getResource();
        //when
        String key = "uid:sign:10086:202311";
        for(long i = 0; i <8; i ++){
            Boolean setbit = jedis.setbit(key, i, true);
        }
        //then
        Boolean getbit = jedis.getbit(key, 15);
        System.out.println(getbit);
    }

    /**
     * BITCOUNT key [start end]
     */
    @Test
    public void bit_count_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();
        //when
        String key = "uid:sign:10086:202310";
        Long bitcount = jedis.bitcount(key, 0, 31);

        //then
        System.out.println(bitcount);
    }
}
