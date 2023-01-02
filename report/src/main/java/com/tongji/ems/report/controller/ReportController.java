package com.tongji.ems.report.controller;

import com.tongji.ems.report.model.Report;
import com.tongji.ems.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> PostaddReport(@RequestParam Long experimentId,@RequestParam Long studentId,@RequestParam String content) {
        if (content.isEmpty()) {
            return ResponseEntity.status(400).body(null);
        }
        Report report=new Report();
        report.setExperimentId(experimentId);
        report.setStudentId(studentId);
        report.setContent(content);
        try {
            return ResponseEntity.ok(reportService.addReport(report));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> PostupdateReport(@RequestParam Long reportId, @RequestParam Long studentId,@RequestParam String content) {
        if (reportId==null||reportId==0) {
            return ResponseEntity.status(400).body(null);
        }
        Report report=new Report();
        report.setReportId(reportId);
        report.setStudentId(studentId);
        report.setContent(content);
        try {
            return ResponseEntity.ok(reportService.updateReport(report));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/assistantscore")
    public ResponseEntity<Map<String, Object>> AssistantscoreReport(@RequestParam Long reportId, @RequestParam Long assiatantId,@RequestParam Float score,@RequestParam String comment) {
        if (reportId==null||reportId==0) {
            return ResponseEntity.status(400).body(null);
        }
        Report report=new Report();
        report.setReportId(reportId);
        report.setAssistantId(assiatantId);
        report.setScore(score);
        report.setComment(comment);
        try {
            return ResponseEntity.ok(reportService.scoreReport(report));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
    @PostMapping("/teacherscore")
    public ResponseEntity<Map<String, Object>> TeacherscoreReport(@RequestParam Long reportId, @RequestParam Long teacherId,@RequestParam Float score,@RequestParam String comment) {
        if (reportId==null||reportId==0) {
            return ResponseEntity.status(400).body(null);
        }
        Report report=new Report();
        report.setReportId(reportId);
        report.setTeacherId(teacherId);
        report.setScore(score);
        report.setComment(comment);
        try {
            return ResponseEntity.ok(reportService.scoreReport(report));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> checkReport(@RequestParam Long experimentId) {
        try {
            return ResponseEntity.ok(reportService.getReports(experimentId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }
    @GetMapping("/getone")
    public ResponseEntity<Map<String, Object>> getReport(@RequestParam Long experimentId,@RequestParam Long studentId) {
        try {
            return ResponseEntity.ok(reportService.getReport(experimentId,studentId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

}
