package com.study;

/**
 * 测试基于栈架构的JVM
 *
 *
 */
public class TestStackStru {
    static int num = 2;

    public static void main(String[] args) {
        //编译完成后用  javap -v [classes路径] 来反编译字节码文件
       // int i = 2 +3;
        int i = 2;
        int j = 3;
        int k = i + j;
        System.out.println(k);
    }
}
