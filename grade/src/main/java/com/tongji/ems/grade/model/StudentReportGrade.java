package com.tongji.ems.grade.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("student_report_grade")
public class StudentReportGrade {
    private Long studentId;
    private Long courseId;
    private Long reportId;
    private Long experimentId;
    private Float score;
    private Long reviewerId;
    private Date reviewTime;

    public StudentReportGrade() {
    }

    public StudentReportGrade(Long studentId, Long courseId, Long reportId, Long experimentId, Float score, Long reviewerId, Date reviewTime) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.reportId = reportId;
        this.experimentId = experimentId;
        this.score = score;
        this.reviewerId = reviewerId;
        this.reviewTime = reviewTime;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }
}
