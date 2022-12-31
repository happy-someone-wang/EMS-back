package com.tongji.ems.coursemanage.service.impl;

import com.tongji.ems.coursemanage.mapper.CourseMapper;
import com.tongji.ems.coursemanage.model.Course;
import com.tongji.ems.coursemanage.service.CourseService;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    PersonalInfoClient personalInfoClient;

    @Override
    public Map<String, Object> getCourseById(Long courseId) {
        Map<String, Object> result = new HashMap<>();
        Course course = courseMapper.selectCourseById(courseId);
        result.put("course",course);
        return result;
    }

    @Override
    public List<Long> getOneCourseAllTeachers(Long courseId) {
        return courseMapper.selectOneCourseAllTeacher(courseId);
    }

    @Override
    public List<Long> getOneStudentAllCourses(Long studentId) {
        return courseMapper.selectOneStudentAllCourses(studentId);
    }
}