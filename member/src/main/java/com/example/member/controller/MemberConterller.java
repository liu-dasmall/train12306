package com.example.member.controller;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/123:05
 *
 */

import com.example.common.response.CommonResponse;
import com.example.member.req.MemberRegisterReq;
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
    public CommonResponse hello(){
        long count =  memberService.getMemberCount();
        CommonResponse<Long> response = new CommonResponse<>();
        response.setContent(count);
        return response;
    }

    @GetMapping("/register")
    public CommonResponse register(MemberRegisterReq resq){
        return memberService.register(resq);
    }
}
