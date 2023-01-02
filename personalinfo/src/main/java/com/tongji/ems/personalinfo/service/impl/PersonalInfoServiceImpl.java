package com.tongji.ems.personalinfo.service.impl;

import com.tongji.ems.feign.clients.FileStoreClient;
import com.tongji.ems.personalinfo.mapper.StudentInfoMapper;
import com.tongji.ems.personalinfo.mapper.TeacherInfoMapper;
import com.tongji.ems.personalinfo.model.Student;
import com.tongji.ems.personalinfo.model.Teacher;
import com.tongji.ems.personalinfo.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Autowired
    private FileStoreClient fileStoreClient;

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

    @Override
    public Map<String, Object> postPersonalInfo(Object person, String role) {
        Map<String, Object> result = new HashMap<>();
        if (role.equals("student")) {
            Student student = (Student) person;
            if (student == null) {
                result.put("code", 400);
                return result;
            }
            if (student.getStudentId() == null) {
                result.put("code", 400);
                return result;
            }
            Student temp = studentInfoMapper.selectById(student.getStudentId());
            if (temp != null) {
                result.put("code", 400);
                result.put("desc", "该用户已存在");
                return result;
            }
            studentInfoMapper.insert(student);
            result.put("code", 200);
            result.put("student", student);
            return result;
        } else if (role.equals("teacher")) {
            Teacher teacher = (Teacher) person;
            if (teacher == null || teacher.getTeacherId() == null) {
                result.put("code", 400);
                return result;
            }
            Teacher temp = teacherInfoMapper.selectById(teacher.getTeacherId());
            if (temp != null) {
                result.put("code", 400);
                result.put("desc", "该用户已存在");
                return result;
            }
            teacherInfoMapper.insert(teacher);
            result.put("code", 200);
            result.put("teacher", teacher);
            return result;
        } else {
            result.put("code", 400);
            return result;
        }
    }

    @Override
    public Map<String, Object> updatePersonalInfo(Object person, String role) {
        Map<String, Object> result = new HashMap<>();
        if (role.equals("student")) {
            Student student = (Student) person;
            if (student == null || student.getStudentId() == null) {
                result.put("code", 400);
                return result;
            }
            Student temp = studentInfoMapper.selectById(student.getStudentId());
            if (temp == null) {
                result.put("code", 400);
                return result;
            }
            if (student.getEmail() != null) {
                temp.setEmail(student.getEmail());
            }
            if (student.getPhone() != null) {
                temp.setPhone(student.getPhone());
            }
            if (student.getSelfDesc() != null) {
                temp.setSelfDesc(student.getSelfDesc());
            }
            if (student.getResidence() != null) {
                temp.setResidence(student.getResidence());
            }
            if (student.getTags() != null) {
                temp.setTags(student.getTags());
            }
            studentInfoMapper.updateById(temp);
            result.put("student", temp);
            result.put("code", 200);
            return result;
        } else if (role.equals("teacher")) {
            Teacher teacher = (Teacher) person;
            if (teacher == null || teacher.getTeacherId() == null) {
                result.put("code", 400);
                return result;
            }
            Teacher temp = teacherInfoMapper.selectById(teacher.getTeacherId());
            if (temp == null) {
                result.put("code", 400);
                return result;
            }
            if (teacher.getEmail() != null) {
                temp.setEmail(temp.getEmail());
            }
            if (teacher.getPhone() != null) {
                temp.setPhone(teacher.getPhone());
            }
            if (teacher.getSelfDesc() != null) {
                temp.setSelfDesc(teacher.getSelfDesc());
            }
            if (teacher.getTags() != null) {
                temp.setTags(teacher.getTags());
            }
            result.put("code", 200);
            result.put("teacher", temp);
            teacherInfoMapper.updateById(temp);
            return result;
        } else {
            result.put("code", 400);
            return result;
        }
    }

    @Override
    public Boolean deletePersonalInfo(Integer Id, String role) {
        if (Id == null || Id <= 0) {
            return false;
        }
        if (role.equals("student")) {
            studentInfoMapper.deleteById(Id);
            return true;
        } else if (role.equals("teacher")) {
            teacherInfoMapper.deleteById(Id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String changeAvatar(MultipartFile avatar, Integer Id, String role) {
        if (Id == null || Id <= 0) {
            return null;
        }
        System.out.println("personalInfo接收到文件"+avatar.getName());
        if (role.equals("teacher")) {
            String avatarUrl = fileStoreClient.uploadFile(avatar);
            Teacher teacher = teacherInfoMapper.selectById(Id);
            if (teacher != null) {
                teacher.setAvatar(avatarUrl);
                teacherInfoMapper.updateById(teacher);
                return avatarUrl;
            }
        } else if (role.equals("student")) {
            String avatarUrl = fileStoreClient.uploadFile(avatar);
            Student student = studentInfoMapper.selectById(Id);
            if (student != null) {
                student.setAvatar(avatarUrl);
                studentInfoMapper.updateById(student);
                System.out.println(student);
                return avatarUrl;
            }
        } else {
            return null;
        }
        return null;
    }
}
