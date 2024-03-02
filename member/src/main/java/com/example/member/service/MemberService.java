package com.example.member.service;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/214:11
 *
 */

import com.example.member.domain.Member;
import com.example.member.domain.MemberExample;
import com.example.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;
    public long getMemberCount(){
        return memberMapper.countByExample(null);
    }

    public long register(String mobile){

        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
//        if(ColUtils.isNotEmpty(members)){
//            return -999;
//        }
        if(members != null && members.size() !=0){
            return -999;
        }

        Member member = new Member();
        member.setMobile(mobile);
        member.setId(System.currentTimeMillis());
        int insert = memberMapper.insert(member);
        return insert;

    }
}
