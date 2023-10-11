package com.felix;


import io.rebloom.client.Client;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;

/**
 * BF.RESERVE bf_order_id 0.01 1000 EXPANSION 2
 * <p>
 * BF.ADD bf_order_id 123456789
 * <p>
 * BF.EXISTS bf_order_id 123456789
 */
public class RedisBloomTest {

    private static final Logger log = LoggerFactory.getLogger(RedisBloomTest.class);


    @Test
    public void rebloom_bloom_test() {
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxTotal(10);
        conf.setTestOnBorrow(false);
        conf.setTestOnReturn(false);
        conf.setTestOnCreate(false);
        conf.setTestWhileIdle(false);
        conf.setMinEvictableIdleTimeMillis(60000);
        conf.setTimeBetweenEvictionRunsMillis(30000);
        conf.setNumTestsPerEvictionRun(-1);
        conf.setFairness(true);

        JedisPool jedisPool = new JedisPool(conf, "192.168.159.111", 6379, 500, "redis123", 0);
        Client client = new Client(jedisPool);

        String bfKey = "bf_order_id";
        boolean exists = client.exists(bfKey, "123456789");

        System.out.println(exists);
    }

    /**
     * public enum Command implements ProtocolCommand {
     *     RESERVE("BF.RESERVE"),
     *     ADD("BF.ADD"),
     *     MADD("BF.MADD"),
     *     EXISTS("BF.EXISTS"),
     *     MEXISTS("BF.MEXISTS"),
     *     INSERT("BF.INSERT"),
     *     INFO("BF.INFO");
     *
     *     private final byte[] raw;
     *
     *     Command(String alt) {
     *         raw = SafeEncoder.encode(alt);
     *     }
     *
     *     public byte[] getRaw() {
     *         return raw;
     *     }
     * }
     */
    @Test
    public void jedis_bloom_test() {
        String bfKey = "bf_order_id";

        Jedis jedis = new Jedis("192.168.159.111", 6379);
        jedis.auth("redis123");
        jedis.select(0);
        Object o = jedis.sendCommand(() -> "BF.EXISTS".getBytes(StandardCharsets.UTF_8), bfKey, "123456789");

        System.out.println(o);

    }

}
