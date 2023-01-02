package com.tongji.ems.Resource.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@TableName("resource")
public class Resource {
    @TableId(value = "resource_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long resourceId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long teacherId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long courseId;

    private String type;

    private Long fileSize;

    private String fileName;

    private String filePath;

    public Resource(Long resourceId, Long teacherId, Long courseId, String type, Long fileSize, String fileName, String filePath) {
        this.resourceId = resourceId;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.type = type;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Resource() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
