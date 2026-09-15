package com.test;


import com.test.urlparser.UrlParser;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/14/17:05
 * @Description:
 */
public class Test2 {
    public static void main(String[] args) {
        String url = "http://192.168.1.35:8001/intelligentQuestionAnswering/financialDocVerify";

        System.out.println(UrlParser.parseUrl(url));
    }
}
