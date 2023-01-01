package com.tongji.ems.coursemanage.service;

import com.tongji.ems.coursemanage.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public interface CourseService {
    Course getCourseById(Long courseId);

    List<Long> getOneCourseAllTeachers(Long courseId);

    List<Long> getOneStudentAllCourses(Long studentId);

    int addExperiment(Course course);

    int modifyExperiment(Long courseId, String name, String credit, String startTime, String endTime);

    int removeExperiment(Long courseId);
}
