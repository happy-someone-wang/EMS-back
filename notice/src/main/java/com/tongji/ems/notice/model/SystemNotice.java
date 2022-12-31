package com.tongji.ems.notice.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 系统通知
 *
 * @author 赵帅涛
 * @since 2022年12月31日
 */
@TableName("system_notice")
public class SystemNotice {
    @TableId("notice_id")
    private Long noticeId;
    private String title;
    private String content;
    private Date createTime;
    private Boolean top;

    public SystemNotice() {
    }

    public SystemNotice(Long noticeId, String title, String content, Date createTime, Boolean top) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.top = top;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
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

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }
}
