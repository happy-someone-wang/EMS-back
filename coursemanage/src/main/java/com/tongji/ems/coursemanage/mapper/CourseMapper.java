package com.tongji.ems.coursemanage.mapper;

import com.tongji.ems.coursemanage.model.Course;
import com.tongji.ems.coursemanage.model.TeacherTeachCourse;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT student_id FROM student_join_course WHERE course_id=${courseId}")
    List<Long> selectOneCourseAllStudents(@Param("courseId") Long courseId);

    @Select("SELECT * FROM teacher_teach_course WHERE teacher_id=${teacherId}")
    List<TeacherTeachCourse> selectOneTeacherAllCourses(@Param("teacherId") Long teacherId);

    @Insert("INSERT into course (course_id,name,credit,start_time,end_time) " + "values(#{courseId},#{name},#{courseId},#{startTime},#{endTime})")
    int insertExperiment(Course course);

    @Update("UPDATE course SET name='${name}',credit='${credit}',start_time='${startTime}',end_time='${endTime}' WHERE course_id='${courseId}'")
    int updateExperiment(@Param("courseId") Long courseId, @Param("name") String name, @Param("credit") String credit, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Delete("DELETE FROM course WHERE course_id=${courseId}")
    int deleteExperiment(@Param("courseId") Long courseId);
}
