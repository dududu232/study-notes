package com.study.heap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/01/9:33
 * @Description:  查看堆内存演示
 *    -Xms10m   堆初始空间
 *    -Xmx20m   堆最大空间
 */
public class HeapDemo {
    public static void main(String[] args) {
        System.out.println("start...");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }
}
