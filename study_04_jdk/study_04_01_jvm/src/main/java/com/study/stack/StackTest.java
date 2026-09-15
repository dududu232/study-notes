package com.study.stack;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/24/11:26
 * @Description:
 */
public class StackTest {
    private int a =1;
    public static void main(String[] args) {
        System.out.println("main开始执行...");
        StackTest stackTest = new StackTest();
        stackTest.method1();
        System.out.println("main执行结束...");

    }

    public void method1(){
        System.out.println("方法一开始执行...");
        method2();
        System.out.println("方法一执行结束...");
    }

    public int method2() {
        System.out.println("方法二开始执行...");
        int i = 10;
        int m = (int)method3();
        System.out.println("方法二执行结束...");
        return m;
    }

    public double method3() {
        System.out.println("方法三开始执行...");
        double j = 20.0;
        System.out.println("方法三即将结束...");
        return j;
    }

    public String method4(String s){
        System.out.println(s);
        return s;
    }




}
