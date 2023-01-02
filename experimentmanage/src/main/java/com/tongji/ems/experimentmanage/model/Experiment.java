package com.tongji.ems.experimentmanage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@TableName("experiment")
public class Experiment {
    @TableId(value = "experiment_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long experimentId;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    private Date createTime;
    private Date deadline;
    private String introduction;
    private String content;

    public Experiment() {
    }

    public Experiment(Long experimentId, String name, Long courseId, Date createTime, Date deadline, String introduction, String content) {
        this.experimentId = experimentId;
        this.name = name;
        this.courseId = courseId;
        this.createTime = createTime;
        this.deadline = deadline;
        this.introduction = introduction;
        this.content = content;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
