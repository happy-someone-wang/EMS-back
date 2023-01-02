package com.tongji.ems.admin.controller;

public class User {
    private Long id;
    private String name;
    private String type;
    private Integer gender;

    private String email;

    private String phone;

    private String passwd;


    public User(Long id, String name, String type, Integer gender, String email, String phone, String passwd) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.passwd = passwd;
    }
    public User(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public User() {

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
