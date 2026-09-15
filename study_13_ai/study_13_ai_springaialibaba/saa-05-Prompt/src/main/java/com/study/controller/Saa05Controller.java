package com.study.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/12/09:55
 * @Description:
 */
@RestController
public class Saa05Controller {

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


    @GetMapping("/prompt/chat")
    public Flux<String> chatflux(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        return deepSeekChatClient.prompt()
                .system("你是一个法律助手，只回答法律问题。其他问题回复：我只能回答法律问题，其他无可奉告")
                .user(msg)
                .stream()
                .content();
    }



    @GetMapping("/prompt/chatflux2")
    public Flux<ChatResponse> chatflux2(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        SystemMessage systemMessage = new SystemMessage("你是一个讲故事的助手，每个故事控制在300字以内");

        UserMessage userMessage = new UserMessage(msg);

        Prompt prompt = new Prompt(systemMessage, userMessage);


        return deepSeekModel.stream(prompt);
    }



    @GetMapping("/prompt/chatflux3")
    public Flux<String> chatflux3(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        SystemMessage systemMessage = new SystemMessage("你是一个讲故事的助手，每个故事控制在300字以内");

        UserMessage userMessage = new UserMessage(msg);

        Prompt prompt = new Prompt(systemMessage, userMessage);


        return deepSeekModel.stream(prompt)
                .map(resp->resp.getResults().get(0).getOutput().getText());
    }


    @GetMapping("/prompt/chatflux4")
    public Flux<String> chatflux4(@RequestParam(defaultValue = "你是谁",name = "msg") String msg){
        return qwenChatClient.prompt(msg).stream().content();
    }


    @GetMapping("/prompt/chat5")
    public String chat5(@RequestParam(name = "city") String city){
        String text = deepSeekChatClient.prompt().user(city + "未来三天天气如何？")
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

        //工具调用

        ToolResponseMessage responseMessage = new ToolResponseMessage(List.of(new ToolResponseMessage.ToolResponse("1", "获得天气", city)));
        String toolMsg = responseMessage.getText();


        return toolMsg;
    }
}
