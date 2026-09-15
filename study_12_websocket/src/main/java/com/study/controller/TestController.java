package com.study.controller;

import com.study.config.NativeWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private NativeWebSocketServer webSocketServer;

    private Session session;
    private CountDownLatch latch = new CountDownLatch(1);

    /**
     * 连接到WebSocket服务器
     */
    @RequestMapping("/connect")
    @ResponseBody
    public String connect() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            // 建立连接
            session = container.connectToServer(new MyWebSocketClient(),
                    URI.create("ws://localhost:8888/ws"));

            // 等待连接建立
            boolean connected = latch.await(5, TimeUnit.SECONDS);

            if (connected && session != null && session.isOpen()) {
                return "连接成功! Session ID: " + session.getId();
            } else {
                return "连接失败或超时";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "连接失败: " + e.getMessage();
        }
    }

    /**
     * 发送文本消息
     */
    @RequestMapping("/sendText")
    @ResponseBody
    public String sendText(String msg) {
        if (session == null || !session.isOpen()) {
            return "错误: 连接未建立，请先调用/connect";
        }

        try {
            session.getBasicRemote().sendText(msg);
            return "消息发送成功: " + msg;
        } catch (IOException e) {
            e.printStackTrace();
            return "消息发送失败: " + e.getMessage();
        }
    }

    /**
     * 断开连接
     */
    @RequestMapping("/disconnect")
    @ResponseBody
    public String disconnect() {
        if (session != null && session.isOpen()) {
            try {
                session.close();
                session = null;
                latch = new CountDownLatch(1);
                return "连接已断开";
            } catch (IOException e) {
                e.printStackTrace();
                return "断开连接失败: " + e.getMessage();
            }
        }
        return "连接已断开或未建立";
    }

    /**
     * 检查连接状态
     */
    @RequestMapping("/status")
    @ResponseBody
    public String status() {
        if (session == null) {
            return "未连接";
        } else if (session.isOpen()) {
            return "已连接, Session ID: " + session.getId();
        } else {
            return "连接已关闭";
        }
    }

    @RequestMapping("/serverSendMsg")
    @ResponseBody
    public String serverSendMsg(String msg){
        webSocketServer.send(msg);
        return "success";
    }

    /**
     * 内部WebSocket客户端类
     */
    @ClientEndpoint
    public class MyWebSocketClient {

        @OnOpen
        public void onOpen(Session session) {
            System.out.println("连接已建立: " + session.getId());
            latch.countDown();
        }

        @OnMessage
        public void onMessage(String message) {
            System.out.println("收到消息: " + message);
        }

        @OnClose
        public void onClose(Session session, CloseReason closeReason) {
            System.out.println("连接已关闭: " + closeReason);
        }

        @OnError
        public void onError(Session session, Throwable throwable) {
            System.err.println("WebSocket错误: " + throwable.getMessage());
        }
    }




    @Value("${engine.websocket.serverUrl}")
    private String serverUrl = "1";


    @RequestMapping("/getServerUrl")
    @ResponseBody
    public String getServerUrl(){
        return serverUrl;
    }

}
