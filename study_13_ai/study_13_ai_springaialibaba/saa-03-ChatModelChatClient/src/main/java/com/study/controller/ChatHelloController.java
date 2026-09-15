package com.study.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @Autowired
    private ChatClient chatClient;


    @GetMapping("/doChat")
    public String doChat(@RequestParam(defaultValue = "1加3",name = "msg") String msg){
        return chatClient.prompt().user(msg).call().content();
    }


    @GetMapping("/streamChat")
    public Flux<String> streamChat(@RequestParam(defaultValue = "你好",name = "msg")String msg){
        return chatModell.stream(msg);
    }



}
