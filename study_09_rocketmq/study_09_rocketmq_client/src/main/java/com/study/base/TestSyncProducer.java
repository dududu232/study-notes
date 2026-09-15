package com.study.base;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/06/22:07
 * @Description: 测试同步消息
 */
public class TestSyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.222.128:9876");
        //3.启动producer
        producer.start();
        //4.创建消息对象，指定主题Topic、Tag和消息体
        for (int i = 0; i < 10; i++) {
            //5.发送消息
            Message message = new Message("Topic1", "tag1", ("Hello World" + i).getBytes());
            SendResult result = producer.send(message);
            System.out.println(result);
        }

        //6.关闭生产者producer
        Thread.sleep(5000);
        producer.shutdown();
    }
}
