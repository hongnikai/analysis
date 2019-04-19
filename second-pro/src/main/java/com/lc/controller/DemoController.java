package com.lc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {



    @RequestMapping("/demo")
    public String demo(){
        System.out.println("测试二类");
        return "adsasd";
    }





}
