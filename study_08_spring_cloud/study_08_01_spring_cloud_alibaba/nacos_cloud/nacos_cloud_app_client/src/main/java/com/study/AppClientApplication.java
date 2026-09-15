package com.study;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/02/15:46
 * @Description:
 */
@SpringBootApplication
@EnableDubbo
@EnableDubboConfig
public class AppClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppClientApplication.class,args);
    }
}
