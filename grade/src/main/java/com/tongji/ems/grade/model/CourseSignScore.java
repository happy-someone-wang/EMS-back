package com.tongji.ems.grade.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("course_sign_score")
public class CourseSignScore {
    @TableId("course_id")
    private Long courseId;
    private Float experiment;
    private Float sign;

    public CourseSignScore() {
    }

    public CourseSignScore(Long courseId, Float experiment, Float sign) {
        this.courseId = courseId;
        this.experiment = experiment;
        this.sign = sign;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Float getExperiment() {
        return experiment;
    }

    public void setExperiment(Float experiment) {
        this.experiment = experiment;
    }

    public Float getSign() {
        return sign;
    }

    public void setSign(Float sign) {
        this.sign = sign;
    }
}
