package com.tongji.ems.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.report.mapper.ReportMapper;
import com.tongji.ems.report.model.Report;
import com.tongji.ems.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.tongji.ems.report.util.GenerateIdTenth.get10UniqueId;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Map<String, Object> addReport(Report report) {
        Map<String, Object> result = new HashMap<>();
        report.setReportId(get10UniqueId());
        report.setUploadTime(new Date());
        try {
            reportMapper.insert(report);
            result.put("status","插入成功");
        }catch (Exception e) {
            result.put("status","插入失败");
        }
        return result;
    }
    @Override
    public Map<String, Object> updateReport(Report report) {
        Map<String, Object> result = new HashMap<>();
        report.setUploadTime(new Date());
        try {
            reportMapper.updateById(report);
            result.put("status","更新成功");
        }catch (Exception e) {
            result.put("status","更新失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> getReports(Long experimentId) {
        Map<String, Object> result = new HashMap<>();
        List<Report> reportList;
        QueryWrapper<Report> queryWrapper = new QueryWrapper<Report>()
                .eq("experiment_id", experimentId);
        try {
            reportList = reportMapper.selectList(queryWrapper);
            result.put("status","读取成功");
            result.put("reportList",reportList);
        }catch (Exception e) {
            result.put("status","读取失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> scoreReport(Report report) {
        Map<String, Object> result = new HashMap<>();
        try {
            reportMapper.updateById(report);
            result.put("status","打分完成");
        }catch (Exception e) {
            result.put("status","打分失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> getReport(Long experimentId, Long studentId) {
        Map<String, Object> result = new HashMap<>();
        List<Report> reportList;
        QueryWrapper<Report> queryWrapper = new QueryWrapper<Report>()
                .eq("experiment_id", experimentId)
                .eq("student_id",studentId);
        try {
            reportList = reportMapper.selectList(queryWrapper);
        }catch (Exception e) {
            result.put("status","读取失败");
            return result;
        }
        if(reportList.isEmpty()) {
            result.put("status","未提交报告");
        }
        else{
            result.put("status","已提交报告");
        }
        result.put("reportList",reportList);
        return result;
    }
}
