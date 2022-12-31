package com.tongji.ems.personalinfo.service.impl;

import com.tongji.ems.personalinfo.mapper.StudentInfoMapper;
import com.tongji.ems.personalinfo.mapper.TeacherInfoMapper;
import com.tongji.ems.personalinfo.model.Student;
import com.tongji.ems.personalinfo.model.Teacher;
import com.tongji.ems.personalinfo.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Override
    public Map<String, Object> getPersonalInfo(Integer id, String role) {
        if (id == null || id < 0 || role == null) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        if (role.equals("student")) {
            Student student = studentInfoMapper.selectById(id);
            if (student == null) {
                result.put("status", "查无此人");
            } else {
                result.put("status", "查询成功");
                result.put("studentId", student.getStudentId());
                result.put("gender", student.getGender());
                result.put("name", student.getName());
                result.put("email", student.getEmail());
                result.put("avatar", student.getAvatar());
                result.put("school", student.getSchool());
                result.put("startYear", student.getStartYear());
                result.put("selfDesc", student.getSelfDesc());
                result.put("residence", student.getResidence());
                result.put("tags", student.getTags());
            }
            return result;
        } else if (role.equals("teacher")) {
            Teacher teacher = teacherInfoMapper.selectById(id);
            if (teacher == null) {
                result.put("status", "查无此人");
            } else {
                result.put("status", "查询成功");
                result.put("teacherId", teacher.getTeacherId());
                result.put("name", teacher.getName());
                result.put("email", teacher.getEmail());
                result.put("gender", teacher.getGender());
                result.put("avatar", teacher.getAvatar());
                result.put("selfDesc", teacher.getSelfDesc());
                result.put("tags", teacher.getTags());
            }
            return result;
        } else {
            return null;
        }
    }
}
