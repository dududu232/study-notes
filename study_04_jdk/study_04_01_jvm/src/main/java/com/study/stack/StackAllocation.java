package com.study.stack;

import com.study.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/2:02
 * @Description:  栈上分配测试
 */
public class StackAllocation {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            test();
        }

        System.out.println(System.currentTimeMillis() - l);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void test(){
        StackTest test = new StackTest();
    }
}
