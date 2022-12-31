package com.tongji.ems.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Student {
    @TableId(value = "student_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long student_id;

    private String name;

    private Integer gender;

    private String email;

    private String phone;

    private String passwd;

    public Student(Long student_id, String name, Integer gender, String email, String phone, String passwd) {
        this.student_id = student_id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.passwd = passwd;
    }

    public Student() {

    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
