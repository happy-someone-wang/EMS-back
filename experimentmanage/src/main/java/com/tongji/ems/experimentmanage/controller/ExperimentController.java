package com.tongji.ems.experimentmanage.controller;

import com.tongji.ems.experimentmanage.model.Experiment;
import com.tongji.ems.experimentmanage.service.ExperimentService;
import com.tongji.ems.experimentmanage.util.GenerateIdTenth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@CrossOrigin
@RestController
@RequestMapping("/experiment")
public class ExperimentController {
    @Autowired
    ExperimentService experimentService;

    @GetMapping("/getExperimentList")
    public ResponseEntity<List<Experiment>> getExperimentList(
            @RequestParam(value = "courseId") Long courseId
    ) {
        try {
            ArrayList<Experiment> experiments = new ArrayList<>();
            List<Long> experimentIds = experimentService.getOneCourseAllExperiment(courseId);
            for (Long Id : experimentIds) {
                Experiment experimentInfo = experimentService.getExperimentById(Id);
                experiments.add(experimentInfo);
            }

            return ResponseEntity.ok(experiments);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/postExperiment")
    public ResponseEntity<String> postExperiment(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "courseId") Long courseId,
            @RequestParam(value = "createTime") String createTime,
            @RequestParam(value = "deadline") String deadline,
            @RequestParam(value = "introduction") String introduction
    ) {
        try {
            Long experimentId = GenerateIdTenth.get10UniqueId();
            Date createTime_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
            Date deadline_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deadline);
            Experiment experiment = new Experiment(experimentId, name, courseId, createTime_date, deadline_date, introduction);
            int result = experimentService.addExperiment(experiment);
            if (result == 1) {
                return ResponseEntity.ok("插入成功");
            } else {
                return ResponseEntity.status(400).body("插入失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/putExperiment")
    public ResponseEntity<String> putExperiment(
            @RequestParam(value = "experimentId") Long experimentId,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "deadline") String deadline,
            @RequestParam(value = "introduction") String introduction
    ) {
        try {
            int result = experimentService.modifyExperiment(experimentId, name, deadline, introduction);
            if (result == 1) {
                return ResponseEntity.ok("修改成功");
            } else {
                return ResponseEntity.status(400).body("修改失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/deleteExperiment")
    public ResponseEntity<String> deleteExperiment(
            @RequestParam(value = "experimentId") Long experimentId
    ) {
        try {
            int result = experimentService.removeExperiment(experimentId);
            if (result == 1) {
                return ResponseEntity.ok("删除成功");
            } else {
                return ResponseEntity.status(400).body("删除失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
