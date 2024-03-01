package com.example.member.controller;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/123:05
 *
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testConterller {
    @GetMapping("/hello")
    public String hello(){
        return "hello member!!!";
    }
}
