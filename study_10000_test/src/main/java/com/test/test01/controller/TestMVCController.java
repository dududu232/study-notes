package com.test.test01.controller;

import com.test.test01.domain.Clazz;
import com.test.test01.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TestMVCController {

    @GetMapping("/testMvc")
    public String testMvc(){
        System.out.println("访问了");
        return "testMvc";
    }

    @PostMapping("/testMvc")
    @ResponseBody
    public String  test(Clazz clazz){
        return "ccc";
    }


    @GetMapping("/testMvc2")
    public String testMvc2(){
        return "testMvc2";
    }

    @PostMapping("/testMvc2")
    @ResponseBody
    public String test2(Student student){

        return "dd";
    }

    @GetMapping("/testMvcMap")
    public String testMvcMap(){
        return "testMvcMap";
    }

    @PostMapping("/testMvcMap")
    @ResponseBody
    public String testMvcMap(Map<String,String> map, HttpServletRequest request){

        return "dd";
    }

}
