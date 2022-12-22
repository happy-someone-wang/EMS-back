package com.tongji.ems.personalinfo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("teacher")
public class Teacher {
    @TableId("teacher_id")
    private Integer teacherId;
    private String name;
    private String gender;
    private String email;
    private String phone;

    public Teacher() {
    }

    public Teacher(Integer teacherId, String name, String gender, String email, String phone) {
        this.teacherId = teacherId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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
}
