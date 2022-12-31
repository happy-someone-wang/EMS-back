package com.tongji.ems.experimentmanage.mapper;

import com.tongji.ems.experimentmanage.model.Experiment;
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
public interface ExperimentMapper {
    @Select("SELECT * FROM experiment WHERE experiment_id=${experimentId}")
    Experiment selectExperimentById(@Param("experimentId") Long experimentId);

    @Select("SELECT experiment_id FROM experiment WHERE course_id=${courseId}")
    List<Long> selectOneCourseAllExperiments(@Param("courseId") Long courseId);
}
