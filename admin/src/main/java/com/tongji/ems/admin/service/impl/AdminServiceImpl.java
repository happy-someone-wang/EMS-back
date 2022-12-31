package com.tongji.ems.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.admin.controller.User;
import com.tongji.ems.admin.mapper.StudentMapper;
import com.tongji.ems.admin.mapper.TeacherMapper;
import com.tongji.ems.admin.model.Student;
import com.tongji.ems.admin.model.Teacher;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import com.tongji.ems.admin.mapper.AssistantMapper;
import com.tongji.ems.admin.model.Assistant;
import com.tongji.ems.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 赵帅涛
 * @since 2022年12月22日
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AssistantMapper assistantMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PersonalInfoClient personalInfoClient;

    @Override
    public Map<String, Object> addUsers(List<User> Users) {
        if (Users.isEmpty()) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        Integer success=0;
        for (int i = 0; i < Users.size(); i++) {
            User oneuser=Users.get(i);
            switch (oneuser.getType()){
                case "助教":
                    Assistant assistant=new Assistant();
                    assistant.setAssistant_id(oneuser.getSno());
                    assistant.setName(oneuser.getName());
                    assistant.setPasswd("Tj123456");
                    try {
                        assistantMapper.insert(assistant);
                    }catch(Exception e) {
                    }
                    success++;
                    break;
                case "教师":
                    Teacher teacher=new Teacher();
                    teacher.setTeacher_id(oneuser.getSno());
                    teacher.setName(oneuser.getName());
                    teacher.setPasswd("Tj123456");
                    try {
                        teacherMapper.insert(teacher);
                    }catch(Exception e) {
                    }
                    success++;
                    break;
                case "学生":
                    Student student=new Student();
                    student.setStudent_id(oneuser.getSno());
                    student.setName(oneuser.getName());
                    student.setPasswd("Tj123456");
                    try {
                        studentMapper.insert(student);
                    }catch(Exception e) {
                    }
                    success++;
                    break;
                default:
                    break;
            }
        }
        if(success!=Users.size())
        {
            result.put("status", "插入不成功");
            result.put("successNum", success);
        }
        else{
            result.put("status", "插入不成功");
            result.put("successNum", success);
        }
        return result;
    }
}
