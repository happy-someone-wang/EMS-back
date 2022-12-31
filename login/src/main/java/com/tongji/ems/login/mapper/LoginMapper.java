package com.tongji.ems.login.mapper;

import com.tongji.ems.login.model.Student;
import com.tongji.ems.login.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Mapper
public interface LoginMapper {
    @Select("SELECT * FROM student WHERE student_id=${studentId}")
    Student selectStudentById(@Param("studentId") Long studentId);

    @Select("SELECT * FROM teacher WHERE teacher_id=${teacherId}")
    Teacher selectTeacherById(@Param("teacherId") Long teacherId);
}
