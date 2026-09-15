package com.study;


public class TestClassInit {
    private static int num = 1;   //这里必须是static修饰的,因为初始化阶段只关注静态变量
    private static  TestStackStru testStackStru = new TestStackStru();

    static {
        num = 2;
    }

    public static void main(String[] args) {
        System.out.println(num);
    }
}
