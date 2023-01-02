package com.tongji.ems.experimentmanage.service.impl;

import com.tongji.ems.experimentmanage.mapper.ExperimentMapper;
import com.tongji.ems.experimentmanage.model.Experiment;
import com.tongji.ems.experimentmanage.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    ExperimentMapper experimentMapper;

    @Override
    public Experiment getExperimentById(Long experimentId) {
        return experimentMapper.selectExperimentById(experimentId);
    }

    @Override
    public List<Long> getOneCourseAllExperiment(Long courseId) {
        return experimentMapper.selectOneCourseAllExperiments(courseId);
    }

    @Override
    public int addExperiment(Experiment experiment) {
        return experimentMapper.insertExperiment(experiment);
    }

    @Override
    public int modifyExperiment(Long experimentId, String name, String deadline, String introduction) {
        return experimentMapper.updateExperiment(experimentId, name, deadline, introduction);
    }

    @Override
    public int removeExperiment(Long experimentId) {
        return experimentMapper.deleteExperiment(experimentId);
    }
}
