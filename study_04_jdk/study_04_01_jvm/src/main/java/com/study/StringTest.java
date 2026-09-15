package com.study;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/21:47
 * @Description:
 */
public class StringTest {
    public static void main(String[] args) {
        System.out.print(1);
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");

        StringTest test = new StringTest();
        test.test3();
    }

    public void test2(){
        String a = "1";
        String b = "2";
        String c = a+b;
    }

    public void test3(){
        StringBuilder builder = new StringBuilder(2);
        builder.append("1");
        builder.append("1");
        builder.append("1");
        builder.append("1");
        builder.append("1");
        System.out.println(builder);
    }
}
