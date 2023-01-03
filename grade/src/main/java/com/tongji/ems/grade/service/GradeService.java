package com.tongji.ems.grade.service;

import com.tongji.ems.grade.model.*;

import java.util.Map;

public interface GradeService {
    /**
     * 教师发布签到
     * 学生签到
     * 教师批改实验报告打分
     * 获取学生课程成绩
     * 获取学生所有课程的成绩
     * 获取课程内所有学生的成绩
     */

    public Map<String, Object> publishCourseSignIn(CourseSign courseSign);

    public Map<String, Object> StudentSignIn(StudentSign studentSign);

    public Map<String, Object> getCourseSignInList(Long courseId, Long studentId);

    public Map<String, Object> getStudentCourseSignInList(Long studentId, Long courseId);

    public Map<String, Object> publishReportScore(StudentReportGrade studentReportGrade);

    public Map<String, Object> getStudentCourseGrade(Long studentId, Long courseId);

    public Map<String, Object> getCourseAllStudentGrade(Long courseId);

    public Map<String, Object> getStudentAllCourseGrade(Long studentId);

    public Map<String, Object> getCourseGradeProportion(Long courseId);

    public Map<String, Object> postCourseGradeProportion(CourseSignScore courseSignScore);

    public Map<String, Object> postCourseExperimentProportion(CourseExperimentScore courseExperimentScore);
}
