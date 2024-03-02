package com.example.member.service;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/214:11
 *
 */

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.*;
import com.example.common.exception.BusinessException;
import com.example.common.response.CommonResponse;
import com.example.member.domain.Member;
import com.example.member.domain.MemberExample;
import com.example.member.mapper.MemberMapper;
import com.example.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.common.exception.BusinessExceptionEnum.MEMBER_MOBILE_EXIST;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;
    public long getMemberCount(){
        return memberMapper.countByExample(null);
    }

    public CommonResponse register(MemberRegisterReq registerReq){
        CommonResponse<Long> response = new CommonResponse<>();
        String mobile = registerReq.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(members)){
            throw new BusinessException(MEMBER_MOBILE_EXIST);
        }


        Member member = new Member();
        member.setMobile(mobile);
        long id = IdUtil.getSnowflake(1, 1).nextId();
        member.setId(id);
        int insert = memberMapper.insert(member);
        response.setContent((long)insert);
        response.setMessage("注册成功");
        return response;

    }
}
