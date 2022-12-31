package com.tongji.ems.experimentmanage.controller;

import com.tongji.ems.experimentmanage.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@RestController
@RequestMapping("/experiment")
public class ExperimentController {
    @Autowired
    ExperimentService experimentService;

    @GetMapping("/getExperimentList")
    public ResponseEntity<List<Map<String, Object>>> getExperimentList(
            @RequestParam(value  = "courseId") Long courseId
    ) {
        try {
            ArrayList<Map<String, Object>> experiments = new ArrayList<>();
            List<Long> experimentIds = experimentService.getOneCourseAllExperiment(courseId);
            for (Long Id : experimentIds) {
                Map<String, Object> experimentInfo = experimentService.getExperimentById(Id);
                experiments.add(experimentInfo);
            }

            return ResponseEntity.ok(experiments);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


}
