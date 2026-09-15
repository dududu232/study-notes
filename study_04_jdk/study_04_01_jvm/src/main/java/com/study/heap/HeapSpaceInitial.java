package com.study.heap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/01/10:53
 * @Description:
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        //获取Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //获取Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println(" -Xms : " + initialMemory + "M");
        System.out.println(" -Xmx : " + maxMemory + "M");

        System.out.println("系统内存大小为: " + initialMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为: " + maxMemory * 4.0 / 1024 + "G");


       /* try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
