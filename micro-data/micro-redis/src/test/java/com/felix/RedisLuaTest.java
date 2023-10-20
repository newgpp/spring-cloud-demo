package com.felix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author felix
 * @desc some desc
 */
public class RedisLuaTest {

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
     * EVAL script numkeys [key [key ...]] [arg [arg ...]]
     * eval：执行 Lua 脚本的命令。
     * lua-script：Lua 脚本内容。
     * numkeys：表示的是 Lua 脚本中需要用到多少个 key，如果没用到则写 0。
     * key [key ...]：将 key 作为参数按顺序传递到 Lua 脚本，numkeys 是 0 时则可省略。
     * arg：Lua 脚本中用到的参数，如果没有可省略。
     * <p>
     * KEYS[1] 代表第一个参数
     * ARGV[1] 代表第一个值
     */
    @Test
    public void redis_lua_set_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();
        //when
        jedis.eval("redis.call('set', KEYS[1], ARGV[1])", 1, "age", "18");
        //then
        Object age = jedis.eval("return redis.call('get', KEYS[1])", 1, "age");
        System.out.println(age);
    }


    @Test
    public void redis_lua_plus_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();
        //when
        Object eval = jedis.eval("return ARGV[1] + ARGV[2]", 0, "3", "5");
        //then
        System.out.println(eval);
    }

    /**
     * 上传脚本 script load "return ARGV[1]+ARGV[2]"
     * 删除脚本 script flush
     * 是否存在 script exists d276e87013315bf8173fef064c465afaf314303a
     */
    @Test
    public void redis_load_scripts_should_success() {
        //given
        Jedis jedis = jedisPool.getResource();
        //when
        Object o = jedis.evalsha("35d1c20238a1b14d70b8b50f9bbe1ca96d74f077", 0, "1", "2");
        //then
        System.out.println(o);
    }
}
