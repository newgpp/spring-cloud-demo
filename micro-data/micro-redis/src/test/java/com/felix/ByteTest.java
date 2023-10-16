package com.felix;

import org.junit.Test;

/**
 * @author felix
 * @desc some desc
 */
public class ByteTest {

    @Test
    public void bit_op_should_success() {
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
        int not = ~Integer.parseInt("11000000", 2);
        System.out.println(Integer.toBinaryString(not));

        System.out.println("-------------------------------");
        System.out.println("异或运算: 相同为0 不同为1");
        System.out.println("11000000 ^ 11111111");
        int xor = Integer.parseInt("11000000", 2) ^ Integer.parseInt("11111111", 2);
        System.out.println(Integer.toBinaryString(xor));
    }
}
