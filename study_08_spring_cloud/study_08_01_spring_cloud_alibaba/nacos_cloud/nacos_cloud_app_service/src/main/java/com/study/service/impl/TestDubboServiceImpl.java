package com.study.service.impl;

import com.study.service.TestDubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.bouncycastle.util.test.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/03/15:14
 * @Description:
 */
@DubboService(group = "study",version = "1.0")
public class TestDubboServiceImpl implements TestDubboService {
    @Override
    public String testDubbo() {
        System.out.println("provider被调用success");
        return "success";
    }
}
