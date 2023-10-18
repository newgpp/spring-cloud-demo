package com.felix.utils;

import org.junit.Test;

/**
 * @author felix
 * @desc some desc
 */
public class IdUtilTest {

    @Test
    public void generate_id() {

        for (int i = 0; i < 10; i++) {
            long id = IdUtil.nextSnowflakeId();
            System.out.println(id + "  " + System.currentTimeMillis());
        }
    }

}