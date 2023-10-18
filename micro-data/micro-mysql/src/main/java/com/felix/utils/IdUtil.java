package com.felix.utils;

/**
 * @author admin
 * Id生成器
 */
public class IdUtil {

    public static final Snowflake SNOWFLAKE = new Snowflake();

    /**
     * 生成雪花ID
     */
    public static long nextSnowflakeId() {
        return SNOWFLAKE.nextId();
    }


}
