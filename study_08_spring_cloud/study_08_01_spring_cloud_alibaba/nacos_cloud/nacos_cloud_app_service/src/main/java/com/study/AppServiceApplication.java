package com.study;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/02/16:07
 * @Description:
 */
@SpringBootApplication
@EnableDubbo
@EnableDubboConfig
public class AppServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppServiceApplication.class,args);
    }
}
