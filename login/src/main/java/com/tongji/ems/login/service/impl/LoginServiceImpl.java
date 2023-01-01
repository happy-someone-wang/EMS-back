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

    @Override
    public int activateStudent(Long studentId, String password, String email) {
        return loginMapper.updateStudent(studentId, password, email);
    }

    @Override
    public int activateTeacher(Long teacherId, String password, String email) {
        return loginMapper.updateTeacher(teacherId, password, email);
    }
}
