package com.example.member.service;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/214:11
 *
 */

import com.example.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;
    public long getMemberCount(){
        return memberMapper.countByExample(null);
    }

    public long register(String mobile){
        if(memberMapper.sel)

    }
}
