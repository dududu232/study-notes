package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2024/06/04/12:18
 * @Description:  测试部署nginx
 */
@Controller
//@CrossOrigin
public class TestController01 {

    @Autowired
    private TestController01 testController01;

    @ResponseBody
    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        System.out.println(111);

        if (request.getRemoteAddr().equals("127.0.0.1")){
            return "22";
        }
        return "11";
    }
}
