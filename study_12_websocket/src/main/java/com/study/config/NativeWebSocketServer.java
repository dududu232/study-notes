package com.study.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原生WebSocket服务端端点
 * 处理 ws://localhost:8888/ws 连接
 */
@Component
@ServerEndpoint("/ws")
public class NativeWebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(NativeWebSocketServer.class);

    // 存储所有连接的会话，key为sessionId
    private static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    // 在线连接计数器
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    // 当前会话
    private Session session;

    // 用户标识
    private String userId;

    /**
     * 连接建立成功时的回调方法
     */
    @OnOpen
    public void onOpen(Session session) {
        SESSIONS.put(session.getId(),session);
    }

    /**
     * 接收到客户端消息时的回调方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
    }

    @OnClose
    public void onClose(Session session){
        SESSIONS.remove(session.getId());
    }



    public void send(String msg){
        try {
            for (Session value : SESSIONS.values()) {
                value.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
