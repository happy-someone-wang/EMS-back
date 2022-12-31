package com.tongji.ems.login.service;

import com.tongji.ems.login.model.Student;
import com.tongji.ems.login.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public interface LoginService {
    Student getStudentById(Long studentId);

    Teacher getTeacherById(Long teacherId);
}