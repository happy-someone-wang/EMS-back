package com.tongji.ems.notice.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("notice_file")
public class NoticeFile {
    @TableId("file_id")
    private Integer fileId;
    private String fileName;
    private Long noticeId;
    private String noticeType;

    private String storePath;

    public NoticeFile() {
    }

    public NoticeFile(Integer fileId, String fileName, Long noticeId, String noticeType, String storePath) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.noticeId = noticeId;
        this.noticeType = noticeType;
        this.storePath = storePath;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }
}
