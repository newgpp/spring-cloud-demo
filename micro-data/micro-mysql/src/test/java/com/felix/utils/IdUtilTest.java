package com.felix.utils;

import org.junit.Test;

import java.util.regex.Pattern;

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
    
    @Test
    public void test_02(){
        Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        String date01 = "1";
        System.out.println("日期: " + date01 + "  校验通过: " + datePattern.matcher(date01).matches());

        String date02 = "2023-10-19";
        System.out.println("日期: " + date02 + "  校验通过: " + datePattern.matcher(date02).matches());


        System.out.println("2023-10-19".compareTo("2023-10-20"));
    }

}