package com.felix;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisCasTest {

    private static final Logger log = LoggerFactory.getLogger(RedisOpsTest.class);

    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;
    private RedisCommands<String, String> commands;

    @Before
    public void init() {
        RedisURI redisURI = RedisURI.Builder
                .redis("192.168.159.111", 6379)
                .withPassword("redis123")
                .withDatabase(0)
                .build();
        redisClient = RedisClient.create(redisURI);
        connection = redisClient.connect();
        commands = connection.sync();

    }

    /**
     * redis CAS事务
     */
    @Test
    public void redis_watch_multi_exec() {
        boolean b = supplyBalance(9);
    }

    /**
     * 增加库存-乐观锁
     */
    public boolean supplyBalance(int num){
        String key = "balance";
        //MULTI EXEC中代码根据WATCH的值是否发生改变而决定执行还是回滚
        commands.watch(key);
        String s = commands.get(key);
        int current = s == null ? 0 : Integer.parseInt(s);
        int value = current + num;
        if(value > 100){
            log.error("====> 库存最大为100, 当前值 {}, 增大后 {}, 不再增加库存", current, value);
            return false;
        }
        //开启事务
        commands.multi();
        commands.incrby(key, num);
        //执行MULTI后的操作
        TransactionResult execResult = commands.exec();
        //如果watch的key对应的value在watch后被修改 返回true
        boolean b = execResult.wasDiscarded();
        if(b){
            //递归增加库存
            supplyBalance(num);
        }
        log.error("====> 库存补充成功, 当前值 {}", value);
        return true;
    }

    /**
     * 扣减库存
     */
    public boolean deductBalance(int num) {
        String key = "balance";
        //MULTI EXEC中代码根据WATCH的值是否发生改变而决定执行还是回滚
        commands.watch(key);
        String s = commands.get(key);
        int current = s == null ? 0 : Integer.parseInt(s);
        int value = current - num;
        if(value < 0){
            log.error("====> 库存最小值 0, 当前值 {}, 增大后 {}, 不再增加库存", current, value);
            return false;
        }
        //开启事务
        commands.multi();
        commands.incrby(key, num);
        //执行MULTI后的操作
        TransactionResult execResult = commands.exec();
        //如果watch的key对应的value在watch后被修改 返回true
        boolean b = execResult.wasDiscarded();
        if(b){
            //递归扣减库存
            deductBalance(num);
        }
        log.error("====> 库存补充成功, 当前值 {}", value);
        return true;
    }
}
