package com.study.pcRegister;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/24/8:40
 * @Description: 测试pc寄存器
 */
public class PCRegisterTest {
    public static void main(String[] args) {

    }

    public static int test(){
        int i  = 10;
        int j = 20;
        int k = i + j;


        String s = "abc";
        System.out.println(i);
        System.out.println(k);
        return 1;
    }

    public int getSum(){
        int a = 1;
        int b = 10000;
        return a+b;
    }

    public int testGetSum(){
        ArrayList<Object> list = new ArrayList<>();
        int i =5+ getSum();
        int i1 = 6 + getSum();
        int test = PCRegisterTest.test();
        int j = 10;
        return i+j;
    }
}
