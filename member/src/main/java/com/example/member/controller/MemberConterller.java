package com.example.member.controller;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/123:05
 *
 */

import com.example.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberConterller {
    @Resource
    private MemberService memberService;
    @GetMapping("/count")
    public int hello(){
        return memberService.getMemberCount();
    }
}
