package com.tongji.ems.notice.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 课程通知
 *
 * @author 赵帅涛
 * @since 2022年12月31日
 */
@TableName("course_notice")
public class CourseNotice {
    @TableId(value = "notice_id")
    private Long noticeId;
    private Integer teacherId;
    private Integer courseId;
    private String title;
    private String content;
    private Date createTime;
    private Boolean top;

    public CourseNotice() {
    }

    public CourseNotice(Long noticeId, Integer teacherId, Integer courseId, String title, String content, Date createTime, Boolean top) {
        this.noticeId = noticeId;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.top = top;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
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
