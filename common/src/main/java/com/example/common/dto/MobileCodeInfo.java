package com.example.common.dto;/*
 *
 *    @Author:liuyanxiang
 *    @Date: 2024/3/223:12
 *
 */

public class MobileCodeInfo {

    private String Mobile;

    private String code;

    private long sendTime = System.currentTimeMillis();

    private Long validTime = sendTime + 1000*60;

    private boolean used = false;

    private String workType;

    private long useTime;

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

    public Long getValidTime() {
        return validTime;
    }

    public void setValidTime(Long validTime) {
        this.validTime = validTime;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    @Override
    public String toString() {
        return "MobileCodeInfo{" +
                "Mobile='" + Mobile + '\'' +
                ", code='" + code + '\'' +
                ", validTime=" + validTime +
                ", used=" + used +
                ", workType='" + workType + '\'' +
                ", sendTime=" + sendTime +
                ", useTime=" + useTime +
                '}';
    }
}
