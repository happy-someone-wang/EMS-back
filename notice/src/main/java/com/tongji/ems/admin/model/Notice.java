package com.tongji.ems.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("notice")
public class Notice {
    @TableId(value = "notice_id")
    private Integer noticeId;
    private Integer teacherId;
    private Integer courseId;
    private String title;
    private String content;
    private Date createTime;

    public Notice() {
    }

    public Notice(Integer noticeId, Integer teacherId, Integer courseId, String title, String content, Date createTime) {
        this.noticeId = noticeId;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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
