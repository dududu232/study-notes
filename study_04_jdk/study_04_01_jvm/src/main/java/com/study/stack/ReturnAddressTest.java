package com.study.stack;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/31/17:02
 * @Description: 测试方法返回指令
 */
public class ReturnAddressTest {
    // ireturn
    public byte byteTest()  {
        System.out.println(111);
        try {
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(22);
        }
        byte a =  1;
        return a;
    }

    //ireturn
    public short shortTest() {
        return 1;
    }

    //ireturn
    public int intTest() {
        return 1;
    }

    //lreturn
    public long longTest() {
        return 1;
    }

    //freturn
    public float floatTest() {
        return 1;
    }

    //dreturn
    public double doubleTest() {
        return 1;
    }

    //ireturn
    public boolean booleanTest() {
        return true;
    }

    // ireturn
    public char aCharTest() {
        return '1';
    }

    //areturn
    public String stringTest() {
        return "1";
    }

    //return
    public ReturnAddressTest() {
    }
}
