package com.study.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/03/17:40
 * @Description:
 */
@RestController
public class ChatHelloController {

    @Autowired
    private ChatModel chatModell;




    @GetMapping("/doChat")
    public String doChat(@RequestParam(defaultValue = "你好",name = "msg") String msg){
        return chatModell.call(msg);
    }


    @GetMapping("/streamChat")
    public Flux<String> streamChat(@RequestParam(defaultValue = "你好",name = "msg")String msg){
        return chatModell.stream(msg);
    }



}
