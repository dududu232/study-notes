package com.study.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/12/09:55
 * @Description:
 */
@RestController
@RequestMapping("prompt-template")
public class Saa06Controller {

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

    @Value("classpath:/propmpt-template/aa.txt")
    private Resource userTemplate;


   @GetMapping("/chat")
   public Flux<String> chat(
           @RequestParam("topic") String topic,
           @RequestParam("outputFormat") String outputFormat,
           @RequestParam("wordCount") String wordCount
   ){
       PromptTemplate promptTemplate = new PromptTemplate("讲一个关于{topic}的故事\n" +
               "并以{outputFormat}格式输出\n" +
               "字数在{wordCount}左右\n");

       Map<String, Object> topic1 = Map.of(
               "topic", topic,
               "outputFormat", outputFormat,
               "wordCount", wordCount
       );

       Prompt prompt = promptTemplate.create(topic1);

       return deepSeekChatClient.prompt(prompt).stream().content();
   }


   @GetMapping("/chat2")
    public Flux<String> chat2(
           @RequestParam("topic") String topic,
           @RequestParam("outputFormat") String outputFormat,
           @RequestParam("wordCount") String wordCount
   ){
       PromptTemplate promptTemplate = new PromptTemplate(userTemplate);
       Prompt prompt = promptTemplate.create(Map.of(
               "topic", topic,
               "outputFormat", outputFormat,
               "wordCount", wordCount
       ));

       return deepSeekChatClient.prompt(prompt).stream().content();
   }


   //http://localhost:8006/prompt-template/chat3?systemTopic=%E6%B3%95%E5%BE%8B&userTopic=%E5%8A%B3%E5%8A%A8%E4%BF%9D%E6%8A%A4%E6%B3%95
   @GetMapping("/chat3")
    public String chat3(
           @RequestParam("systemTopic") String systemTopic,
           @RequestParam("userTopic") String userTopic
   ){
       SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate("你是一个{systemTopic}助手，只回答{systemTopic}的问题，以html格式输出。");
       Message sysMsg = systemPromptTemplate.createMessage(Map.of("systemTopic", systemTopic));

       PromptTemplate userTemplate = new PromptTemplate("解释一下{userTopic}");
       Message userMsg = userTemplate.createMessage(Map.of("userTopic", userTopic));

       Prompt prompt = new Prompt(sysMsg,userMsg);
       return deepSeekChatClient.prompt(prompt).call().content();
   }



}
