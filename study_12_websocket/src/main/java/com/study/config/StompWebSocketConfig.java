package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2026/01/15/17:45
 * @Description:
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {

        //支持原生websocket连接方式
        stompEndpointRegistry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");//跨域



        stompEndpointRegistry.addEndpoint("/stomp/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
