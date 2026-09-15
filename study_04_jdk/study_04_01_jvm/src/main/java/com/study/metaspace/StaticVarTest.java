package com.study.metaspace;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/18:10
 * @Description:
 */
public class StaticVarTest {
private static byte[] a = new byte[1024*1024*100];


    public static void main(String[] args) {

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
