package com.study.controller;

import com.study.service.TestDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/02/15:49
 * @Description:
 */
@RestController
public class AppClientController {

    @Autowired
    private RestTemplate restTemplate;

    @DubboReference(group = "study",version = "1.0")
    private TestDubboService testDubboService;


    @RequestMapping("/testNacos")
    public String testNacos() {
        //远程访问地址
        String url = "http://nacos-cloud-appservice/testServiceNacos";
        //远程访问
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println("远程访问成功,返回信息: " + forObject);
        return "测试Nacos注册中心";
    }


    @RequestMapping("/testDubbo")
    public String testDubbo(){
        System.out.println(testDubboService.testDubbo());
        return "测试";
    }

}
