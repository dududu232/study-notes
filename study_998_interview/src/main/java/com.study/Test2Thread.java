package com.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/18/10:04
 * @Description:
 */
public class Test2Thread implements Callable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new Test2Thread());
        Thread thread = new Thread(task);
        thread.start();

        String result = task.get();
        System.out.println(result);
    }

    @Override
    public Object call() throws Exception {
        return "hello";
    }
}
