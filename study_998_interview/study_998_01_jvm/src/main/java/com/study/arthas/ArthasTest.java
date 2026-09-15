package com.study.arthas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/20/20:29
 * @Description:
 */
@Configuration
public class ArthasTest {

    /**
     * CPU飙高模拟
     */
    @Bean
    public static void cpuHigh(){
        System.out.println("执行了");
        List<byte[]> largeObjects = new ArrayList<>();
        try {
            while (true) {
                // 创建一个新的大对象，并加入到列表中
                largeObjects.add(new byte[1024 * 1024]); // 1MB的大小
                // 在这里睡眠一段时间来减慢创建对象的速度
                Thread.sleep(100);
            }
        } catch (OutOfMemoryError e) {
            // 当内存溢出时，打印错误并清理资源
            System.out.println("Out of memory error caught!");
            e.printStackTrace();
            // 释放所有占用的内存
            largeObjects = null;
            System.gc(); // 显式调用垃圾收集器尝试释放内存
        } catch (InterruptedException e) {
           // Thread.currentThread().interrupt();
        }
    }
}
