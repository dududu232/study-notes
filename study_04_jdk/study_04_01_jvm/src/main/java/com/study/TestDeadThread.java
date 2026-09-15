package com.study;

public class TestDeadThread {

    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName() + "开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + "结束");
        };

        new Thread(r,"线程一").start();
        new Thread(r,"线程二").start();
    }



}
class DeadThread{
    static {   //当DeadThrea类被加载时,此代码块执行
        System.out.println(Thread.currentThread().getName()+"初始化!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"初始化结束!");
    }
}
