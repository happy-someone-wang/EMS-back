package com.tongji.ems.coursemanage.mapper;

import com.tongji.ems.coursemanage.model.Course;
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
public interface CourseMapper {
    @Select("SELECT * FROM course WHERE course_id=${courseId}")
    Course selectCourseById(@Param("courseId") Long courseId);

    @Select("SELECT teacher_id FROM teacher_teach_course WHERE course_id=${courseId}")
    List<Long> selectOneCourseAllTeacher(@Param("courseId") Long courseId);

    @Select("SELECT course_id FROM student_join_course WHERE student_id=${studentId}")
    List<Long> selectOneStudentAllCourses(@Param("studentId") Long studentId);
}
