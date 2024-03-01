package com.lyx.train12306.controller;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/122:14
 *
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/hello")
    public String hello(){
        return "hello, 你是dssssdd猪";
    }
}
