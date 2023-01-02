package com.tongji.ems.login.mapper;

import com.tongji.ems.login.model.Student;
import com.tongji.ems.login.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("UPDATE student SET password='${password}',email='${email}' WHERE student_id='${studentId}'")
    int updateStudent(@Param("studentId") Long studentId, @Param("password") String password, @Param("email") String email);

    @Update("UPDATE teacher SET password='${password}',email='${email}' WHERE teacher_id='${teacherId}'")
    int updateTeacher(@Param("teacherId") Long teacherId, @Param("password") String password, @Param("email") String email);

    @Update("UPDATE student SET password='${password}' WHERE student_id='${studentId}'")
    int updateStudentPassword(@Param("studentId") Long studentId, @Param("password") String password);

    @Update("UPDATE teacher SET password='${password}' WHERE teacher_id='${teacherId}'")
    int updateTeacherPassword(@Param("teacherId") Long teacherId, @Param("password") String password);
}
