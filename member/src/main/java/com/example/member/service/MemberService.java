package com.example.member.service;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/214:11
 *
 */

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.*;
import cn.hutool.log.Log;
import com.example.common.cache.MobileCodeCache;
import com.example.common.dto.MobileCodeInfo;
import com.example.common.exception.BusinessException;
import com.example.common.exception.BusinessExceptionEnum;
import com.example.common.response.CommonResponse;
import com.example.member.controller.MemberConterller;
import com.example.member.domain.Member;
import com.example.member.domain.MemberExample;
import com.example.member.mapper.MemberMapper;
import com.example.member.req.MemberLoginReq;
import com.example.member.req.MemberRegisterReq;
import com.example.member.req.MemberSendMobileReq;
import com.example.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.example.common.exception.BusinessExceptionEnum.MEMBER_MOBILE_EXIST;

@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);


    @Resource
    private MemberMapper memberMapper;
    public long getMemberCount(){
        return memberMapper.countByExample(null);
    }

    public CommonResponse register(MemberRegisterReq registerReq){
        CommonResponse<Object> response = new CommonResponse<>();
        String mobile = registerReq.getMobile();
        Member member = selectMemberByMobile(mobile);
        if(member == null){
            throw new BusinessException(MEMBER_MOBILE_EXIST);
        }


         member = new Member();
        member.setMobile(mobile);
        long id = IdUtil.getSnowflake(1, 1).nextId();
        member.setId(id);
        int insert = memberMapper.insert(member);
        response.setContent((long)insert);
        response.setMessage("注册成功");
        return response;

    }

    public void sendCode(MemberSendMobileReq mobileReq){

        String mobile = mobileReq.getMobile();
        Member member = selectMemberByMobile(mobile);
        LOG.info("接受到的手机号是{}", mobile);

//        不存在，则将用户记录保存到表中；
        if(member == null){
            member = new Member();
            member.setMobile(mobile);
            long id = IdUtil.getSnowflake(1, 1).nextId();
            member.setId(id);
            memberMapper.insert(member);
        }
//        生成四位验证码
        String code = RandomUtil.randomString(4);
        LOG.info("验证码是{}", code);

//        保存短信记录表： 手机号，验证码，有效期，是否使用，业务类型，发送时间， 使用时间
        MobileCodeInfo mobileCodeInfo = new MobileCodeInfo();
        mobileCodeInfo.setMobile(mobile);
        mobileCodeInfo.setCode(code);
        MobileCodeCache.mobileCodeCache.put(mobile, mobileCodeInfo);

//        发送短信，对接通道；
    }
    public MemberLoginResp login(MemberLoginReq loginReq){
        MemberLoginResp loginResp = new MemberLoginResp();
        String mobile = loginReq.getMobile();
        Member member = selectMemberByMobile(mobile);
        LOG.info("接受到的手机号是{}", mobile);

//        手机号不存在
        if(member == null){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_NOT_EXIST);
        }
        verifyCode(loginReq);
        BeanUtil.copyProperties(member, loginResp);
        LOG.info("登陆成功，手机号是{}", mobile);
        return loginResp;



    }

    private Member selectMemberByMobile(String mobile){
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(members)){
            return null;
        }
        return members.get(0);
    }

    private boolean verifyCode(MemberLoginReq loginReq){
        MobileCodeInfo codeInfo = MobileCodeCache.mobileCodeCache.get(loginReq.getMobile());
        if(null == codeInfo){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_NOT_EXIST);
        }
//        验证码不正确||验证码已使用||验证码过期
        if(!codeInfo.getCode().equals(loginReq.getCode()) || codeInfo.isUsed() || codeInfo.getValidTime() < System.currentTimeMillis()){
            LOG.info(codeInfo.toString());
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        codeInfo.setUsed(true);
        codeInfo.setUseTime(System.currentTimeMillis());
        MobileCodeCache.mobileCodeCache.put(loginReq.getMobile(), codeInfo);
        return true;
    }
}
