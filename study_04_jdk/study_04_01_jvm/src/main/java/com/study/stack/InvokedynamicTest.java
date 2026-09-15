package com.study.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/31/15:15
 * @Description: 测试invokedynamic指令
 */

interface Fun {
    void test();
}

public class InvokedynamicTest {

    public static void main(String[] args) {
        Fun fun = () -> {
            return;
        };
    }


}
