package com.study.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/01/13:21
 * @Description:
 */
public class OOMTest {

    public static void main(String[] args) {
        Random random = new Random();
       // System.out.println(random.nextInt());  //输出int范围内的随机数
       // System.out.println(random.nextInt(2));  // [0,2)区间内的一个随机数



        ArrayList<Picture> list = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024*12024)));
        }
    }
}


class Picture{
    private byte[] pixels;

    public Picture(int length){
        this.pixels = new byte[length];
    }
}
