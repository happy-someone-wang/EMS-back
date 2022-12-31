package com.tongji.ems.login.service.impl;

import com.tongji.ems.login.mapper.LoginMapper;
import com.tongji.ems.login.model.Student;
import com.tongji.ems.login.model.Teacher;
import com.tongji.ems.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public Student getStudentById(Long studentId) {
        return loginMapper.selectStudentById(studentId);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return loginMapper.selectTeacherById(teacherId);
    }
}
