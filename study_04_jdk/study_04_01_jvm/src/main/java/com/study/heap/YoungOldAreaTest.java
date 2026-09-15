package com.study.heap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/01/19:53
 * @Description: 测试: 大对象直接进入老年代
 *  -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8  -XX:+PrintGCDetails
 */
public class YoungOldAreaTest {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024*1024*20];

    }
}
