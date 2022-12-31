package com.tongji.ems.experimentmanage.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@Service
public interface ExperimentService {
    Map<String, Object> getExperimentById(Long experimentId);

    List<Long> getOneCourseAllExperiment(Long courseId);
}