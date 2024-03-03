package com.example.member.req;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/223:21
 *
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberLoginReq {
    @NotBlank
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式错误")
    private String Mobile;

    @NotBlank
    private String code;

    private String imgCode;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    @Override
    public String toString() {
        return "memberLoginReq{" +
                "Mobile='" + Mobile + '\'' +
                ", code='" + code + '\'' +
                ", imgCode='" + imgCode + '\'' +
                '}';
    }
}
