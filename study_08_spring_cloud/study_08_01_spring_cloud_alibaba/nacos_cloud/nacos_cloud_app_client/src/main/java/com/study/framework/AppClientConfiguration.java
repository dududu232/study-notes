package com.study.framework;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/02/16:23
 * @Description:
 */
@Configuration
public class AppClientConfiguration {
    @Bean
    @LoadBalanced   //使用ribbon负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
