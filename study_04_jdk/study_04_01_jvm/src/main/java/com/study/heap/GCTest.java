package com.study.heap;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/01/17:14
 * @Description:
 */
public class GCTest {
    private static int i  = 0;

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String a = "zhaijiajia";
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            a+=a;
            list.add(a);
            i++;
        }
    }

}
