package com.study.config;


import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/12/09:33
 * @Description:
 */
@Configuration
public class SaaLLMConfig {

    private final String DEEPSEEK_MODEL = "deepseek-v4-pro";
    private final String QWEN_MODEL = "qwen-max";



    @Bean
    public ChatModel deepSeek(){
        return DashScopeChatModel.builder()
                .dashScopeApi(DashScopeApi.builder().apiKey(System.getenv("ai-api-key")).build())
                .defaultOptions(DashScopeChatOptions.builder().withModel(DEEPSEEK_MODEL).build())
                .build();

    }


    @Bean
    public ChatModel qwen(){
        return DashScopeChatModel.builder()
                .dashScopeApi(DashScopeApi.builder().apiKey(System.getenv("ai-api-key")).build())
                .defaultOptions(DashScopeChatOptions.builder().withModel(QWEN_MODEL).build())
                .build();
    }


    @Bean
    public ChatClient deepSeekChatClient(@Qualifier("deepSeek") ChatModel deepSeek){
        return ChatClient.builder(deepSeek).build();
    }


    @Bean
    public ChatClient qwenChatClient(@Qualifier("qwen")ChatModel qwen){
        return ChatClient.builder(qwen).build();
    }



}
