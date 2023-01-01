package com.tongji.ems.grade.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("course_experiment_score")
public class CourseExperimentScore {
    private Long courseId;
    private Long experimentId;
    private Float proportion;
    private Float signProportion;

    public CourseExperimentScore() {
    }

    public CourseExperimentScore(Long courseId, Long experimentId, Float proportion, Float signProportion) {
        this.courseId = courseId;
        this.experimentId = experimentId;
        this.proportion = proportion;
        this.signProportion = signProportion;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    public Float getProportion() {
        return proportion;
    }

    public void setProportion(Float proportion) {
        this.proportion = proportion;
    }

    public Float getSignProportion() {
        return signProportion;
    }

    public void setSignProportion(Float signProportion) {
        this.signProportion = signProportion;
    }
}
