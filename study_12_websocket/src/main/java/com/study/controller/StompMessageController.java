package com.study.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2026/01/16/10:33
 * @Description:
 */
@Controller
public class StompMessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Map<String, Object> handleChat(String message,
                                          Principal principal,
                                          SimpMessageHeaderAccessor headerAccessor) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("sender", principal != null ? principal.getName() : "anonymous");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("sessionId", headerAccessor.getSessionId());
        response.put("type", "chat");

        System.out.println("收到聊天消息: " + message + ", 发送者: " +
                (principal != null ? principal.getName() : "unknown"));

        return response;
    }

}
