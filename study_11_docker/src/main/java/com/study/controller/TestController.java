package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 翟佳佳
 * @Date: 2026/01/07/11:02
 * @Description:
 */
@Controller
@ResponseBody
@RequestMapping("test1")
public class TestController {

    @RequestMapping("/aa")
    public String aa(){
        return "success";
    }
}
