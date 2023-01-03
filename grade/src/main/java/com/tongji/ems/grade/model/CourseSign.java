package com.tongji.ems.grade.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;


@TableName("course_sign")
public class CourseSign {
    @TableId(value = "sign_id",type = IdType.AUTO)
    private Long signId;
    private Long courseId;
    private Date startTime;
    private Date endTime;
    private Long experimentId;

    public CourseSign() {
    }

    public CourseSign(Long signId, Long courseId, Date start_time, Date end_time, Long experimentId) {
        this.signId = signId;
        this.courseId = courseId;
        this.startTime = start_time;
        this.endTime = end_time;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }
}
