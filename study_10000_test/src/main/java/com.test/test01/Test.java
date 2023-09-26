package com.test.test01;

public class Test {
    public static void main(String[] args) {
        Stu stu = new Stu();
        stu.setName("测试");
        String name = stu.getName();

        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(stu.getName());
        }
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(name);
        }
        long l3 = System.currentTimeMillis();

        System.out.println(l2-l);
        System.out.println(l3-l2);
    }
}
