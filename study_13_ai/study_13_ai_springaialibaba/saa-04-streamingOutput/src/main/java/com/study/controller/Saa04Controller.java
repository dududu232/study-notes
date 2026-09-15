package com.study.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/12/09:55
 * @Description:
 */
@RestController
public class Saa04Controller {

    @Qualifier("deepSeek")
    @Autowired
    private ChatModel deepSeekModel;


    @Autowired
    @Qualifier("qwen")
    private ChatModel qwenModel;

    @Autowired
    private ChatClient deepSeekChatClient;
    @Autowired
    private ChatClient qwenChatClient;


    @GetMapping("/stream/chatflux1")
    public Flux<String> chatflux(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        return deepSeekModel.stream(msg);
    }



    @GetMapping("/stream/chatflux2")
    public Flux<String> chatflux2(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        return qwenModel.stream(msg);
    }



    @GetMapping("/stream/chatflux3")
    public Flux<String> chatflux3(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        return deepSeekChatClient.prompt(msg).stream().content();
    }


    @GetMapping("/stream/chatflux4")
    public Flux<String> chatflux4(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        return qwenChatClient.prompt(msg).stream().content();
    }
}
