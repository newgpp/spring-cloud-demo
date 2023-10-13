package com.felix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;

/**
 * @author felix
 * @desc some desc
 */
public class RedisBitfieldTest {

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
     * https://redis.io/commands/bitfield/
     * BITFIELD key [GET encoding offset]
     * BITFIELD key [SET encoding offset]
     * BITFIELD key [INCRBY encoding offset increment]
     *
     * encoding
     * i32 有符号32位 支持负数  max i64
     * u32 无符号32位 不支持负数  max u63
     *
     * offset
     * 如果偏移量以 # 字符为前缀，则指定的偏移量将乘以整数编码的宽度
     * SET i8 #0 10 SET i8 #1 20  将在偏移量 0 处设置第一个 i8 整数，在偏移量 8 处设置第二个 i8 整数。
     *
     * 如果指定了没有任何前缀的数字，则它仅用作字符串内基于零的位偏移量
     */
    @Test
    public void bitfield_set_get_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();
        //when
        String key = "player:1";
        List<Long> bitfield = jedis.bitfield(key, "set u32 #0 1000".split(" "));
        System.out.println(bitfield);
        //then
        List<Long> bitfield1 = jedis.bitfield(key, "get u32 #0".split(" "));
        System.out.println(bitfield1);
    }

    @Test
    public void bitfield_incrby_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();
        //when
        String key = "player:1";
        List<Long> bitfield = jedis.bitfield(key, "incrby u32 #0 1 set u32 #1 5".split(" "));
        System.out.println(bitfield);
        //then
        List<Long> bitfield1 = jedis.bitfield(key, "get u32 #0 get u32 #1".split(" "));
        System.out.println(bitfield1);
        List<Long> longs = jedis.bitfieldReadonly(key, "get u32 #0 get u32 #1".split(" "));
        System.out.println(longs);
    }
}
