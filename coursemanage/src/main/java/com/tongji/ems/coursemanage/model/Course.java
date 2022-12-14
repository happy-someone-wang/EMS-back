package com.tongji.ems.coursemanage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@TableName("course")
public class Course {
    @TableId(value = "course_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    private String name;
    private Float credit;
    private Date startTime;
    private Date endTime;
    private List<String> teacher;
    private Integer level;

    private Integer weekday;
    private Integer startCourse;
    private Integer endCourse;
    private String location;

    public Course() {
    }

    public Course(Long courseId, String name, Float credit, Date startTime, Date endTime, Integer weekday, Integer startCourse, Integer endCourse, String location) {
        this.courseId = courseId;
        this.name = name;
        this.credit = credit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekday = weekday;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
        this.location = location;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Integer getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(Integer startCourse) {
        this.startCourse = startCourse;
    }

    public Integer getEndCourse() {
        return endCourse;
    }

    public void setEndCourse(Integer endCourse) {
        this.endCourse = endCourse;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
