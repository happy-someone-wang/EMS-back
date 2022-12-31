package com.tongji.ems.admin.controller;

public class User {
    private Long sno;
    private String name;
    private String type;

    public User(Long sno, String name, String type) {
        this.sno = sno;
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

    public Long getSno() {
        return sno;
    }

    public void setSno(Long sno) {
        this.sno = sno;
    }
}
