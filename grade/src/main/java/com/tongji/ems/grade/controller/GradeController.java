package com.tongji.ems.grade.controller;

import com.tongji.ems.grade.model.CourseSign;
import com.tongji.ems.grade.model.StudentReportGrade;
import com.tongji.ems.grade.model.StudentSign;
import com.tongji.ems.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping("/publishsign")
    public ResponseEntity<Map<String, Object>> publishCourseSignIn(
            @RequestBody CourseSign courseSign) {
        try {
            return ResponseEntity.ok(gradeService.publishCourseSignIn(courseSign));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/studentsign")
    public ResponseEntity<Map<String, Object>> StudentSignIn(
            @RequestBody StudentSign sign
    ) {
        try {
            return ResponseEntity.ok(gradeService.StudentSignIn(sign));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/courseSignList")
    public ResponseEntity<Map<String, Object>> getCourseSignInList(
            @RequestParam("courseId") String cId,
            @RequestParam(value = "studentId", defaultValue = "0") String sId
    ) {
        try {
            Long courseId = Long.valueOf(cId);
            Long studentId = Long.valueOf(sId);
            return ResponseEntity.ok(gradeService.getCourseSignInList(courseId, studentId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/studentGrade")
    public ResponseEntity<Map<String, Object>> publishReportScore(
            @RequestBody StudentReportGrade studentReportGrade
    ) {
        try {
            return ResponseEntity.ok(gradeService.publishReportScore(studentReportGrade));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/studentGrade")
    public ResponseEntity<Map<String, Object>> getStudentCourseGrade(
            @RequestParam("studentId") String sId,
            @RequestParam("courseId") String cId
    ) {
        try {
            Long studentId = Long.valueOf(sId);
            Long courseId = Long.valueOf(cId);
            return ResponseEntity.ok(gradeService.getStudentCourseGrade(studentId, courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/courseGrade")
    public ResponseEntity<Map<String, Object>> getCourseAllStudentGrade(
            @RequestParam("courseId") String cId
    ) {
        try {
            Long courseId = Long.valueOf(cId);
            return ResponseEntity.ok(gradeService.getCourseAllStudentGrade(courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/studentAllGrade")
    public ResponseEntity<Map<String, Object>> getStudentAllCourseGrade(
            @RequestParam("studentId") String sId
    ) {
        try {
            Long studentId = Long.valueOf(sId);
            return ResponseEntity.ok(gradeService.getStudentAllCourseGrade(studentId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
}
