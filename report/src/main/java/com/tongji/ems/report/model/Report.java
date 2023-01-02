package com.tongji.ems.report.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

@TableName("report")
public class Report {
    @TableId(value = "report_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long reportId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long experimentId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long studentId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long teacherId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long assistantId;

    private String content;

    private Date uploadTime;

    private Float score;

    private String comment;

    public Report(Long reportId, Long experimentId, Long studentId, Long teacherId, Long assistantId, String content, Date uploadTime, Float score) {
        this.reportId = reportId;
        this.experimentId = experimentId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.assistantId = assistantId;
        this.content = content;
        this.uploadTime = uploadTime;
        this.score = score;
    }

    public Report() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Long assistantId) {
        this.assistantId = assistantId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
