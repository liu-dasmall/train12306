package com.example.member.controller;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/123:05
 *
 */

import cn.hutool.log.Log;
import com.example.common.response.CommonResponse;
import com.example.member.req.MemberLoginReq;
import com.example.member.req.MemberRegisterReq;
import com.example.member.req.MemberSendMobileReq;
import com.example.member.resp.MemberLoginResp;
import com.example.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberConterller {
    private static final Logger LOG = LoggerFactory.getLogger(MemberConterller.class);
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
    public CommonResponse register(@Valid MemberRegisterReq resq){
        return memberService.register(resq);
    }

    @PostMapping("send_code")
    public CommonResponse sendCode(@Valid @RequestBody  MemberSendMobileReq sendMobileReq){
        memberService.sendCode(sendMobileReq);
        return new CommonResponse<>();

    }

    @PostMapping("/login")
    public CommonResponse<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq loginReq){
        CommonResponse<MemberLoginResp> response = new CommonResponse<>();
        response.setContent(memberService.login(loginReq));
        return response;
    }
}
