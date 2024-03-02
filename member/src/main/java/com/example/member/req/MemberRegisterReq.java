package com.example.member.req;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/216:32
 *
 */

public class MemberRegisterReq {

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
