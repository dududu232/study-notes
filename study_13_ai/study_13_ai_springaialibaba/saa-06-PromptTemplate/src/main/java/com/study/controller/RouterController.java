package com.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/06/12/11:08
 * @Description:
 */
@Controller
public class RouterController {

    @GetMapping("/index")
    public String index(){
        return "/index";
    }
}
