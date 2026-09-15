package com.study;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/18/10:01
 * @Description:
 */
public class Test1Thread implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new Test1Thread());
        thread.start();
    }


    @Override
    public void run() {
        System.out.println("启动了一个线程");
    }
}
