package com.example.aopTest.controller;

import com.example.aopTest.AOP.TeatAspect;
import com.example.aopTest.AopTestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class TestController {
//    @Autowired
//    private TeatAspect teatAspect;
    private final static Logger log=Logger.getLogger(String.valueOf(TeatAspect.class));
    @GetMapping("/test1")
    public String test() {
        log.info("測試方法執行");
        System.out.println(1/0);
        return "返回訊息";
    }
}
