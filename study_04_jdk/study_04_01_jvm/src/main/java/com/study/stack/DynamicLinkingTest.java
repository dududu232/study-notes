package com.study.stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/31/13:19
 * @Description:
 */
public class DynamicLinkingTest {
    int num = 10;

    public void methodA(){
        System.out.println("methodA()......");
    }

    public void methodB(){
        System.out.println("methodB()......");
        methodA();
        num ++;
    }
}
