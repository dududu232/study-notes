package com.test.test01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class TestDivController {

    private String prefix = "testDiv";

    @GetMapping("/testDiv")
    public String testDiv() throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        return prefix + "/testDiv";
    }
}
