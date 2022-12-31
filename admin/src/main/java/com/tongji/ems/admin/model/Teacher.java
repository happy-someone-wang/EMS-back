package com.tongji.ems.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Teacher {
    @TableId(value = "teacher_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long teacher_id;

    private String name;

    private Integer gender;

    private String email;

    private String phone;

    private String passwd;

    public Teacher(Long teacher_id, String name, Integer gender, String email, String phone, String passwd) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.passwd = passwd;
    }

    public Teacher() {

    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }
}
