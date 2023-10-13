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
//        Boolean getbit = jedis.getbit(key, 14);
//        System.out.println(getbit);

        byte[] bytes = binaryJedis.get(key.getBytes(StandardCharsets.UTF_8));

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

    /**
     *
     */
    @Test
    public void bit_op_should_success(){
        System.out.println(Integer.toBinaryString(192));
        System.out.println(Integer.toBinaryString(255));

        System.out.println("-------------------------------");
        System.out.println("或运算: 有1为1 无1为0");
        System.out.println("11000000 | 11111111");
        int or = Integer.parseInt("11000000", 2) | Integer.parseInt("11111111", 2);
        System.out.println(Integer.toBinaryString(or));

        System.out.println("-------------------------------");
        System.out.println("与运算: 全1为1 有0为0");
        System.out.println("11000000 & 11111111");
        int and = Integer.parseInt("11000000", 2) & Integer.parseInt("11111111", 2);
        System.out.println(Integer.toBinaryString(and));

        System.out.println("-------------------------------");
        System.out.println("非运算: 1变0 0变1");
        System.out.println("~ 11000000");
        int not = ~ Integer.parseInt("11000000", 2);
        System.out.println(Integer.toBinaryString(not));

        System.out.println("-------------------------------");
        System.out.println("异或运算: 相同为0 不同为1");
        System.out.println("11000000 ^ 11111111");
        int xor = Integer.parseInt("11000000", 2) ^ Integer.parseInt("11111111", 2);
        System.out.println(Integer.toBinaryString(xor));
    }

    @Test
    public void bytes_test(){

//        int x = 0xff;
//
//        System.out.println(x == 255);
//
//        System.out.println(Integer.toHexString(192));
//        System.out.println(Integer.toHexString(255));
//

        System.out.println(Integer.toBinaryString(Integer.parseInt("ff", 16)));
        System.out.println(Integer.toBinaryString(Integer.parseInt("fe", 16)));


    }
}
