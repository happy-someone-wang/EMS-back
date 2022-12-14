package com.tongji.ems.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@TableName("assistant")
public class Assistant {
    @TableId(value = "assistant_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long assistantId;

    private String name;

    private Integer gender;

    private String email;

    private String phone;

    private String passwd;

    public Assistant(Long assistantId, String name, Integer gender, String email, String phone, String passwd) {
        this.assistantId = assistantId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.passwd = passwd;
    }

    public Assistant() {

    }

    public Long getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Long assistantId) {
        this.assistantId = assistantId;
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
