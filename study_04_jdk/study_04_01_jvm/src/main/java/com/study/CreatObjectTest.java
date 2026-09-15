package com.study;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/20:09
 * @Description:
 */
public class CreatObjectTest implements Cloneable {
    private long id;

    public static void main(String[] args) {
        try {
            CreatObjectTest creatObjectTest = CreatObjectTest.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public CreatObjectTest() {
        System.out.println("空参构造");
    }

    public CreatObjectTest(Long id) {
        System.out.println("非空参构造");
        this.id = id;
    }

    {
        System.out.println("代码块");
    }
}
