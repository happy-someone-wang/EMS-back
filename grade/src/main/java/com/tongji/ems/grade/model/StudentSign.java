package com.tongji.ems.grade.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("student_sign")
public class StudentSign {
    private Integer signId;
    private Long studentId;
    private Long courseId;
    private Date signTime;
    private Boolean signIn;

    public StudentSign() {
    }

    public StudentSign(Integer signId, Long studentId, Long courseId, Date signTime, Boolean signIn) {
        this.signId = signId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.signTime = signTime;
        this.signIn = signIn;
    }

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
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

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Boolean getSignIn() {
        return signIn;
    }

    public void setSignIn(Boolean signIn) {
        this.signIn = signIn;
    }
}
