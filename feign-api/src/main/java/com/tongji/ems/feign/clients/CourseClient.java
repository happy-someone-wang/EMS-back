package com.tongji.ems.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("course")
public interface CourseClient {
    /**
     * 获取该课程的所有选课学生
     *
     * @param courseId
     * @return
     */
    @GetMapping("/course/courseStudent")
    public List<Long> getCourseStudentList(@RequestParam("courseId") Long courseId);

    /**
     * 获取学生的所有课程
     *
     * @param studentId
     * @return
     */
    @GetMapping("/course/studentCourse")
    public List<Long> getCourseListOfStudent(@RequestParam("studentId") Long studentId);

}
