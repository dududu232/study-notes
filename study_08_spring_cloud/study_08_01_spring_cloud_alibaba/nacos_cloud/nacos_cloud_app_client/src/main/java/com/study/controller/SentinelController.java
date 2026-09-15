package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/04/19:09
 * @Description:
 */
@RestController
public class SentinelController {

    //以下为测试流控
    /**
     *测试预热
     * @return
     */
    @SentinelResource("warmUp")
    @RequestMapping("/warmUp")
    public String warmUp(){
        return "warmUp";
    }

    @RequestMapping("/queque")
    public String queque(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "queque";
    }
}
