package com.study.aicodehelper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/04/13:48
 * @Description:
 */
@Service
@Slf4j
public class AiCodeHelper {


    @Resource
    private ChatModel qwenChatModel;

    //简单对话
    public String chat(String message){
        UserMessage userMessage = UserMessage.from(message);
        return chatWithMessage(userMessage);
    }

    //简单对话 - 多模态
    public String chatWithMessage(UserMessage userMessage){
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("AI 输出：{}", aiMessage.toString());
        return aiMessage.text();
    }
}
