package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/02/16:09
 * @Description:
 */
@RestController
@RefreshScope
public class AppServiceController {

    @Value("${info.name}")
    private String name;
    @Value("${info.remark}")
    private String remark;

    @RequestMapping("/testServiceNacos")
    public String testNacos(){
        System.out.println("调用成功! name: "+ name +"remark:"+remark);
        return "测试Nacos服务端";
    }
}
