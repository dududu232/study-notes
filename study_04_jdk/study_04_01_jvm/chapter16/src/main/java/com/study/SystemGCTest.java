package com.study;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/10/1:03
 * @Description:
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        System.gc();
       /* try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

     //  System.runFinalization();
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}
