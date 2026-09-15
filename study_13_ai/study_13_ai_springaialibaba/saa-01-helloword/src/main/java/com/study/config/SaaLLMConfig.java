package com.study.config;


import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/03/17:09
 * @Description:
 */
@Configuration
public class SaaLLMConfig {


    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;


    @Bean
    public DashScopeApi dashScopeApi(){
        return DashScopeApi.builder().apiKey(apiKey).build();
    }

}
