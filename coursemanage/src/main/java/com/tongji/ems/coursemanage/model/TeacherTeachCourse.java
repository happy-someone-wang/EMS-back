package com.tongji.ems.coursemanage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @Author 2051196 刘一飞
 * @Date 2023/1/2
 * @JDKVersion 17.0.4
 */
@TableName("teacher_teach_course")
public class TeacherTeachCourse {
    @TableId(value = "teacher_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;
    @TableId(value = "course_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    private Integer level;

    public TeacherTeachCourse() {
    }

    public TeacherTeachCourse(Long teacherId, Long courseId, int level) {
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
