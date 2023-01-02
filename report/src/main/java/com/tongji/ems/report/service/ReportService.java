package com.tongji.ems.report.service;

import com.tongji.ems.report.model.Report;

import java.util.List;
import java.util.Map;

public interface ReportService {

    public Map<String, Object> addReport(Report report);

    public Map<String, Object> updateReport(Report report);

    public Map<String, Object> getReports(Long experimentId);

    public Map<String, Object> scoreReport(Report report);

    public Map<String, Object> getReport(Long experimentId, Long studentId);
}
