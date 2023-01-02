package com.tongji.ems.admin.service;

import com.tongji.ems.admin.controller.User;

import java.util.List;
import java.util.Map;

public interface AdminService {

    public Map<String, Object> addUsers(List<User> Users);

    public Map<String,Object> checkUsers(User user);

    public Map<String,Object> updateUser(User user);

    public Map<String,Object> checkNotice();


}
