package com.tongji.ems.experimentmanage.service.impl;

import com.tongji.ems.experimentmanage.mapper.ExperimentMapper;
import com.tongji.ems.experimentmanage.model.Experiment;
import com.tongji.ems.experimentmanage.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> getExperimentById(Long experimentId) {
        Map<String, Object> result = new HashMap<>();
        Experiment experiment = experimentMapper.selectExperimentById(experimentId);
        result.put("course",experiment);
        return result;
    }

    @Override
    public List<Long> getOneCourseAllExperiment(Long courseId) {
        return experimentMapper.selectOneCourseAllExperiments(courseId);
    }
}
