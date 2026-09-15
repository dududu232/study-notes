package com.study.base;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/06/22:38
 * @Description: 测试消费者
 */
public class TestConsumer1 {
    public static void main(String[] args) throws MQClientException {
        //1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("producer_group_name");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.222.128:9876");
        //3.订阅主题Topic和Tag
        consumer.subscribe("Topic1","*");
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt ext : list) {
                    System.out.println(new String(ext.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.启动消费者consumer
        consumer.start();
        System.out.println("消费者1启动成功");
    }
}
