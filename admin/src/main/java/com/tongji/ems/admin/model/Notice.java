package com.tongji.ems.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.sql.Date;

public class Notice {
    @TableId(value = "notice_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long noticeId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long teacherId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long courseId;

    private String title;

    private String content;

    private Date createTime;

    public Notice(Long noticeId, Long teacherId, Long courseId, String title, String content, Date createTime) {
        this.noticeId = noticeId;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
