package com.tongji.ems.coursemanage.controller;

import com.tongji.ems.coursemanage.model.Course;
import com.tongji.ems.coursemanage.service.CourseService;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    PersonalInfoClient personalInfoClient;

//    @GetMapping("/getCourseById")
//    public ResponseEntity<Map<String, Object>> getCourseById(
//            @RequestParam(value = "courseId") Long courseId
//    ) {
//        try {
//            return ResponseEntity.ok(courseService.getCourseById(courseId));
//        } catch (Exception e) {
//            return ResponseEntity.status(400).body(null);
//        }
//    }

//    @GetMapping("/getOneCourseAllTeachers")
//    public ResponseEntity<Map<String, Object>> getOneCourseAllTeachers(
//            @RequestParam(value = "courseId") Long courseId
//    ) {
//        try {
//            return ResponseEntity.ok(courseService.getOneCourseAllTeachers(courseId));
//        } catch (Exception e) {
//            return ResponseEntity.status(400).body(null);
//        }
//    }

    @GetMapping("/getStudentCourseList")
    public ResponseEntity<List<Course>> getStudentCourseList(
            @RequestParam(value  = "studentId") Long studentId
    ) {
        try {
            ArrayList<Course> courses = new ArrayList<>();
            List<Long> coursesIds = courseService.getOneStudentAllCourses(studentId);
            for (Long Id : coursesIds) {
                Course courseInfo = courseService.getCourseById(Id);

                // 获取当前课程所有授课老师信息
                ArrayList<String> teachers = new ArrayList<>();
                List<Long> teacherIds = courseService.getOneCourseAllTeachers(Id);
                for (Long teacherId : teacherIds) {
                    Map<String, Object> teacher = personalInfoClient.getPersonalInfo(teacherId, "teacher");
                    teachers.add((String) teacher.get("name"));
                }
                // 将授课老师信息加入map中
                courseInfo.setTeacher(teachers);

                courses.add(courseInfo);
            }

            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
