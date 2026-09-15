package com.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/05/18/15:37
 * @Description:
 */
public class OOMTest {
    public static void main(String[] args) throws InterruptedException {
        List<Test> tests = new ArrayList<>();   //该对象放在局部变量表中,作为GC roots根节点之一,引用了下面循环中的对象
        while (true){
//            HashMap<String, String> stringStringHashMap = new HashMap<>();
//            stringStringHashMap.put()
        }
    }
}
