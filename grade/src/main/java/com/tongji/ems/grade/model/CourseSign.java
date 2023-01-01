package com.tongji.ems.grade.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;


@TableName("course_sign")
public class CourseSign {
    private Long signId;
    private Long courseId;
    private Date start_time;
    private Date end_time;
    private Long experimentId;

    public CourseSign() {
    }

    public CourseSign(Long signId, Long courseId, Date start_time, Date end_time, Long experimentId) {
        this.signId = signId;
        this.courseId = courseId;
        this.start_time = start_time;
        this.end_time = end_time;
        this.experimentId = experimentId;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }
}
