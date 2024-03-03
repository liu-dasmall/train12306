package com.example.member.req;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/222:28
 *
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberSendMobileReq {
    @NotBlank
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式错误")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "memberSendMobileReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
