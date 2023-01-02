package com.tongji.ems.experimentmanage.mapper;

import com.tongji.ems.experimentmanage.model.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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

    @Insert("INSERT into experiment (experiment_id,name,course_id,create_time,deadline,introduction) " +
            "values(#{experimentId},#{name},#{courseId},#{createTime},#{deadline},#{introduction})")
    int insertExperiment(Experiment experiment);

    @Update("UPDATE experiment SET name='${name}',deadline='${deadline}',introduction='${introduction}' WHERE experiment_id='${experimentId}'")
    int updateExperiment(@Param("experimentId") Long experimentId, @Param("name") String name, @Param("deadline") String deadline, @Param("introduction") String introduction);

    @Delete("DELETE FROM experiment WHERE experiment_id=${experimentId}")
    int deleteExperiment(@Param("experimentId") Long experimentId);
}
