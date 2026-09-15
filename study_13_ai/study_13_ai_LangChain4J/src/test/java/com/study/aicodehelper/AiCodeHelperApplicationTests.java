package com.study.aicodehelper;

import com.study.aicodehelper.ai.AiCodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/04/13:56
 * @Description:
 */
@SpringBootTest
public class AiCodeHelperApplicationTests {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void chat(){
        aiCodeHelper.chat("你好，我是程序员鱼皮");
    }


    @Test
    void chatWithMessage(){
        UserMessage userMessage = UserMessage.from(TextContent.from("请描述图片内容"), ImageContent.from("https://pica.zhimg.com/v2-e6001ea9bee80fac10e361c9bd83e8ab_bh.webp?source=d6434cab"));
        aiCodeHelper.chatWithMessage(userMessage);
    }
}
