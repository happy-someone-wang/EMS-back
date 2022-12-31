package com.tongji.ems.personalinfo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {
    @TableId("student_id")
    private Integer studentId;
    private String name;
    private String gender;
    private String email;
    private String phone;
    // 学院
    private String school;
    // 所属年级
    private String startYear;
    // 头像
    private String avatar;
    // 个人简介
    private String selfDesc;
    // 居住地
    private String residence;
    // 个人标签
    private String tags;

    public Student() {
    }

    public Student(Integer studentId, String name, String gender, String email, String phone, String school, String startYear, String avatar, String selfDesc, String residence, String tags) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.school = school;
        this.startYear = startYear;
        this.avatar = avatar;
        this.selfDesc = selfDesc;
        this.residence = residence;
        this.tags = tags;
    }

    public String getSelfDesc() {
        return selfDesc;
    }

    public void setSelfDesc(String selfDesc) {
        this.selfDesc = selfDesc;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
