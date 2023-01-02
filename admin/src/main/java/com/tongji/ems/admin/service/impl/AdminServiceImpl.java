package com.tongji.ems.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.admin.controller.User;
import com.tongji.ems.admin.mapper.NoticeMapper;
import com.tongji.ems.admin.mapper.StudentMapper;
import com.tongji.ems.admin.mapper.TeacherMapper;
import com.tongji.ems.admin.model.Notice;
import com.tongji.ems.admin.model.Student;
import com.tongji.ems.admin.model.Teacher;
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
    private NoticeMapper noticeMapper;

    @Override
    public Map<String, Object> addUsers(List<User> Users) {
        if (Users.isEmpty()) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        Integer success = 0;
        for (User oneuser : Users) {
            switch (oneuser.getType()) {
                case "助教" -> {
                    Assistant assistant = new Assistant();
                    assistant.setAssistantId(oneuser.getId());
                    assistant.setName(oneuser.getName());
                    assistant.setPasswd("Tj123456");
                    try {
                        assistantMapper.insert(assistant);
                        success++;
                    } catch (Exception e) {
                    }
                }
                case "教师" -> {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(oneuser.getId());
                    teacher.setName(oneuser.getName());
                    teacher.setPasswd("Tj123456");
                    try {
                        teacherMapper.insert(teacher);
                        success++;
                    } catch (Exception e) {
                    }
                }
                case "学生" -> {
                    Student student = new Student();
                    student.setStudentId(oneuser.getId());
                    student.setName(oneuser.getName());
                    student.setPasswd("Tj123456");
                    try {
                        studentMapper.insert(student);
                        success++;
                    } catch (Exception e) {
                    }
                }
                default -> {
                }
            }
        }
        if (success != Users.size()) {
            result.put("status", "插入不成功");
            result.put("successNum", success);
        } else {
            result.put("status", "插入成功");
            result.put("successNum", success);
        }
        return result;
    }

    @Override
    public Map<String, Object> checkUsers(User user) {
        Map<String, Object> result = new HashMap<>();
        switch (user.getType()) {
            case "all":
                Integer userNum=0;
                List<Map<String, Object>> Users = new ArrayList<>();
                List<Assistant> assistantList = assistantMapper.selectList(null);
                List<Teacher> teacherList = teacherMapper.selectList(null);
                List<Student> studentList = studentMapper.selectList(null);
                for (Assistant assistant : assistantList) {
                    Map<String, Object> oneuser = new HashMap<>();
                    oneuser.put("index", assistant.getAssistantId());
                    oneuser.put("name", assistant.getName());
                    oneuser.put("email",assistant.getEmail());
                    oneuser.put("type", "助教");
                    Users.add(oneuser);
                    userNum++;
                }
                for (Teacher teacher : teacherList) {
                    Map<String, Object> oneuser = new HashMap<>();
                    oneuser.put("index", teacher.getTeacherId());
                    oneuser.put("name", teacher.getName());
                    oneuser.put("email",teacher.getEmail());
                    oneuser.put("type", "教师");
                    Users.add(oneuser);
                    userNum++;
                }
                for (Student student : studentList) {
                    Map<String, Object> oneuser = new HashMap<>();
                    oneuser.put("index", student.getStudentId());
                    oneuser.put("name", student.getName());
                    oneuser.put("email",student.getEmail());
                    oneuser.put("type", "学生");
                    Users.add(oneuser);
                    userNum++;
                }
                result.put("status", "查询全部用户");
                result.put("users", Users);
                result.put("usernum",userNum);
                break;
            case "助教":
                QueryWrapper<Assistant> assistantQueryWrapper = new QueryWrapper<Assistant>()
                        .eq("assistant_id", user.getId());
                Assistant assistant = assistantMapper.selectOne(assistantQueryWrapper);
                Map<String, Object> oneuser = new HashMap<>();
                oneuser.put("id", assistant.getAssistantId());
                oneuser.put("name", assistant.getName());
                oneuser.put("type", "助教");
                oneuser.put("email",assistant.getEmail());
                result.put("user", oneuser);
                result.put("status", "查询到一个用户");
                break;
            case "教师":
                QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<Teacher>()
                        .eq("teacher_id", user.getId());
                Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
                Map<String, Object> oneuser1 = new HashMap<>();
                oneuser1.put("id", teacher.getTeacherId());
                oneuser1.put("name", teacher.getName());
                oneuser1.put("type", "教师");
                oneuser1.put("email",teacher.getEmail());
                result.put("user", oneuser1);
                result.put("status", "查询到一个用户");
                break;
            case "学生":
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<Student>()
                        .eq("student_id", user.getId());
                Student student = studentMapper.selectOne(studentQueryWrapper);
                Map<String, Object> oneuser2 = new HashMap<>();
                oneuser2.put("id", student.getStudentId());
                oneuser2.put("name", student.getName());
                oneuser2.put("type", "学生");
                oneuser2.put("email",student.getEmail());
                result.put("user", oneuser2);
                result.put("status", "查询到一个用户");
                break;
            default:
                result.put("status", "类型缺失");
        }
        return result;
    }

    @Override
    public Map<String, Object> updateUser(User user) {
        Map<String, Object> result = new HashMap<>();
        switch (user.getType()) {
            case "助教":
                QueryWrapper<Assistant> assistantQueryWrapper = new QueryWrapper<Assistant>()
                        .eq("assistant_id", user.getId());
                Assistant assistant = new Assistant();
                assistant.setPasswd(user.getPasswd());
                assistant.setName(user.getName());
                assistant.setAssistantId(user.getId());
                assistant.setEmail(user.getEmail());
                assistant.setGender(user.getGender());
                assistant.setPhone(user.getPhone());
                try {
                    assistantMapper.updateById(assistant);
                } catch (Exception e) {
                    result.put("status", "修改失败");
                }
                result.put("status", "修改成功");
                break;
            case "教师":
                QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<Teacher>()
                        .eq("teacher_id", user.getId());
                Teacher teacher = new Teacher();
                teacher.setPasswd(user.getPasswd());
                teacher.setName(user.getName());
                teacher.setTeacherId(user.getId());
                teacher.setEmail(user.getEmail());
                teacher.setGender(user.getGender());
                teacher.setPhone(user.getPhone());
                try {
                    teacherMapper.updateById(teacher);
                } catch (Exception e) {
                    result.put("status", "修改失败");
                }
                result.put("status", "修改成功");
                break;
            case "学生":
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<Student>()
                        .eq("student_id", user.getId());
                Student student = new Student();
                student.setPasswd(user.getPasswd());
                student.setName(user.getName());
                student.setStudentId(user.getId());
                student.setEmail(user.getEmail());
                student.setGender(user.getGender());
                student.setPhone(user.getPhone());
                try {
                    studentMapper.updateById(student);
                } catch (Exception e) {
                    result.put("status", "修改失败");
                }
                result.put("status", "修改成功");
                break;
            default:
                result.put("status", "类型缺失");
        }
        return result;
    }

    @Override
    public Map<String, Object> checkNotice() {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Notice> NoticeQueryWrapper = new QueryWrapper<Notice>()
                .eq("course_id", 0);
        try {
            List<Notice> noticeList=noticeMapper.selectList(NoticeQueryWrapper);
            result.put("noticelist",noticeList);
            result.put("status","查询成功");
        }catch(Exception e) {
            System.out.print(e);
            result.put("status","查询失败");
        }
        return result;
    }
}
