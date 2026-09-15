package com.study.metaspace;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/08/16:04
 * @Description:
 */
public class MethodInnerTest  implements Serializable,Comparable<String> {

    //属性
    public int num = 1;
    private static String str = "请求全";
    //构造器

    //方法
    public void test(){
        int count = 2;
        try {
             count = 10;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }


    public static int test2(){
        return 1;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
