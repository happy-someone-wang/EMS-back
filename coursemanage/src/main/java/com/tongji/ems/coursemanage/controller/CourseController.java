package com.tongji.ems.coursemanage.controller;

import com.tongji.ems.coursemanage.model.Course;
import com.tongji.ems.coursemanage.model.TeacherTeachCourse;
import com.tongji.ems.coursemanage.service.CourseService;
import com.tongji.ems.coursemanage.util.GenerateIdTenth;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    PersonalInfoClient personalInfoClient;

    /**
     * 获取该课程的所有选课学生 远程调用
     *
     * @param courseId
     * @return
     */
    @GetMapping("/courseStudent")
    public List<Long> getCourseStudentList(@RequestParam("courseId") Long courseId) {
        return courseService.getOneCourseAllStudents(courseId);
    }

    /**
     * 获取学生的所有课程 远程调用
     *
     * @param studentId
     * @return
     */
    @GetMapping("/studentCourse")
    public List<Long> getCourseListOfStudent(@RequestParam("studentId") Long studentId) {
        return courseService.getOneStudentAllCourses(studentId);
    }

    /**
     * 根据学生的ID获取到所有课程
     *
     * @param studentId
     * @return
     */
    @GetMapping("/getStudentCourseList")
    public ResponseEntity<List<Course>> getStudentCourseList(
            @RequestParam(value = "studentId") Long studentId
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

    /**
     * 获取某个老师的所有课程，其中同时会返回level，即老师在在该门课程的身份
     *
     * @param teacherId
     * @return
     */
    @GetMapping("/getTeacherCourseList")
    public ResponseEntity<List<Course>> getTeacherCourseList(
            @RequestParam(value = "teacherId") Long teacherId
    ) {
        try {
            ArrayList<Course> courses = new ArrayList<>();
            List<TeacherTeachCourse> teaches = courseService.getOneTeacherAllCourses(teacherId);
            for (TeacherTeachCourse teach : teaches) {
                Course courseInfo = courseService.getCourseById(teach.getCourseId());
                courseInfo.setLevel(teach.getLevel());
                courses.add(courseInfo);
            }

            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * 添加实验课程
     *
     * @param name
     * @param credit
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/postCourse")
    public ResponseEntity<String> postCourse(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "credit") Float credit,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "weekday") Integer weekday,
            @RequestParam(value = "startCourse") Integer startCourse,
            @RequestParam(value = "endCourse") Integer endCourse,
            @RequestParam(value = "location") String location
    ) {
        try {
            Long courseId = GenerateIdTenth.get10UniqueId();
            Date startTime_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
            Date endTime_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
            Course course = new Course(courseId, name, credit, startTime_date, endTime_date,weekday,startCourse,endCourse,location);
            int result = courseService.addExperiment(course);
            if (result == 1) {
                return ResponseEntity.ok("插入成功");
            } else {
                return ResponseEntity.status(400).body("插入失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * 修改实验课程
     *
     * @param courseId
     * @param name
     * @param credit
     * @param startTime
     * @param endTime
     * @return
     */
    @PutMapping("/putCourse")
    public ResponseEntity<String> putCourse(
            @RequestParam(value = "courseId") Long courseId,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "credit") String credit,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "weekday") String weekday,
            @RequestParam(value = "startCourse") String startCourse,
            @RequestParam(value = "endCourse") String endCourse,
            @RequestParam(value = "location") String location
    ) {
        try {
            int result = courseService.modifyExperiment(courseId, name, credit, startTime, endTime,weekday,startCourse,endCourse,location);
            if (result == 1) {
                return ResponseEntity.ok("修改成功");
            } else {
                return ResponseEntity.status(400).body("修改失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * 删除实验课程
     *
     * @param courseId
     * @return
     */
    @DeleteMapping("/deleteCourse")
    public ResponseEntity<String> deleteCourse(
            @RequestParam(value = "courseId") Long courseId
    ) {
        try {
            int result = courseService.removeExperiment(courseId);
            if (result == 1) {
                return ResponseEntity.ok("删除成功");
            } else {
                return ResponseEntity.status(400).body("删除失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
