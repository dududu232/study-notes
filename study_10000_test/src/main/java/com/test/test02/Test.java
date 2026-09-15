package com.test.test02;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/03/17:27
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int a = 1;
        Student student = new Student();
        student.setId("1");
        test(a,student);
        System.out.println(a);
        System.out.println(student.getId());
    }

    public static void test(int a,Student student){
        a = 2;
        student.setId("2");
    }
}
class Student{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
