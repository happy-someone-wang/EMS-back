package com.tongji.ems.experimentmanage.service;

import com.tongji.ems.experimentmanage.model.Experiment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public interface ExperimentService {
    Experiment getExperimentById(Long experimentId);

    List<Long> getOneCourseAllExperiment(Long courseId);

    int addExperiment(Experiment experiment);

    int modifyExperiment(Long experimentId, String name, String deadline, String introduction);

    int removeExperiment(Long experimentId);
}