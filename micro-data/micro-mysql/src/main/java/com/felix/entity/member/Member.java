package com.felix.entity.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * generated at 2023-10-16 14:20:22
 */
public class Member {
    /**
     * id
     */
    private Long memberId;

    /**
     * 名字
     */
    private String memberName;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;

    /**
     * 其他信息
     */
    private String extraJson;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", extraJson='" + extraJson + '\'' +
                '}';
    }
}