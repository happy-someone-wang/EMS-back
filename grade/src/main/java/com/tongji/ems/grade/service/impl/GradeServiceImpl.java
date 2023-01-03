package com.tongji.ems.grade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.feign.clients.CourseClient;
import com.tongji.ems.grade.mapper.*;
import com.tongji.ems.grade.model.*;
import com.tongji.ems.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private CourseSignMapper courseSignMapper;
    @Autowired
    private CourseExperimentScoreMapper courseExperimentScoreMapper;

    @Autowired
    private StudentSignMapper studentSignMapper;
    @Autowired
    private StudentReportGradeMapper studentReportGradeMapper;

    @Autowired
    private CourseSignScoreMapper courseSignScoreMapper;

    @Autowired
    private CourseClient courseClient;

    // 发布课程签到
    @Override
    public Map<String, Object> publishCourseSignIn(CourseSign courseSign) {
        Map<String, Object> result = new HashMap<>();
        if (courseSign.getCourseId() == null || courseSign.getExperimentId() == null) {
            result.put("code", 400);
            result.put("desc", "请求参数错误");
            return result;
        }
        if (courseSign.getStartTime() == null || courseSign.getEndTime() == null) {
            result.put("code", 400);
            result.put("desc", "未设置签到时间");
            return result;
        }
        if (courseSign.getStartTime().after(courseSign.getEndTime())) {
            result.put("code", 400);
            result.put("desc", "签到时间设置有误");
            return result;
        }
        courseSignMapper.insert(courseSign);
        result.put("code", 200);
        courseSign.setSignId(courseSign.getSignId());
        result.put("sign", courseSign);
        return result;
    }

    // 学生签到
    @Override
    public Map<String, Object> StudentSignIn(StudentSign studentSign) {
        Map<String, Object> result = new HashMap<>();
        if (studentSign.getSignId() == null || studentSign.getStudentId() == null ||
                studentSign.getCourseId() == null) {
            result.put("code", 400);
            result.put("desc", "请求参数存在空值");
            return result;
        }
        Date signTime = new Date();
        CourseSign courseSign = courseSignMapper.selectById(studentSign.getSignId());
        if (courseSign == null) {
            result.put("code", 400);
            result.put("desc", "不存在的签到项");
            return result;
        }
        if (signTime.before(courseSign.getStartTime())) {
            result.put("code", 400);
            result.put("desc", "签到尚未开始");
            return result;
        }
        if (signTime.after(courseSign.getEndTime())) {
            result.put("code", 400);
            result.put("desc", "签到已结束");
            return result;
        }
        if (signTime.after(courseSign.getStartTime()) && signTime.before(courseSign.getEndTime())) {
            result.put("code", 200);
            result.put("desc", "签到成功");
            studentSign.setSignIn(true);
            studentSign.setSignTime(signTime);
            studentSignMapper.insert(studentSign);
            result.put("sign", studentSign);
            return result;
        }
        return null;
    }

    @Override
    public Map<String, Object> getCourseSignInList(Long courseId, Long studentId) {
        Map<String, Object> result = new HashMap<>();
        if (courseId == null) {
            result.put("code", 400);
            result.put("desc", "课程不存在");
            return result;
        }
        List<CourseSign> courseSignList = courseSignMapper.selectList(new QueryWrapper<CourseSign>()
                .eq("course_id", courseId)
                .orderByDesc("start_time"));
        if (courseSignList == null) {
            result.put("code", 200);
            result.put("desc", "该课程暂无签到");
            return result;
        }
        result.put("code", 200);

        if (studentId != null && studentId != 0) {
            List<Map<String, Object>> signList = new ArrayList<>();
            for (CourseSign courseSign : courseSignList) {
                Map<String, Object> signItem = new HashMap<>();
                StudentSign studentSign = studentSignMapper.selectOne(new QueryWrapper<StudentSign>()
                        .eq("sign_id", courseSign.getSignId())
                        .eq("student_id", studentId));
                signItem.put("sign_id", courseSign.getSignId());
                signItem.put("course_id", courseSign.getCourseId());
                signItem.put("startTime", courseSign.getStartTime());
                signItem.put("endTime", courseSign.getEndTime());
                signItem.put("experimentId", courseSign.getExperimentId());
                if (studentSign != null) {
                    signItem.put("signTime", studentSign.getSignTime());
                    signItem.put("signIn", studentSign.getSignIn());
                } else {
                    signItem.put("signIn", false);
                }
                signList.add(signItem);
            }
            result.put("signList", signList);
            result.put("length", signList.size());
            return result;
        }
        result.put("signList", courseSignList);
        result.put("length", courseSignList.size());
        return result;
    }

    @Override
    public Map<String, Object> getStudentCourseSignInList(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public Map<String, Object> publishReportScore(StudentReportGrade studentReportGrade) {
        Map<String, Object> result = new HashMap<>();
        if (studentReportGrade.getCourseId() == null ||
                studentReportGrade.getExperimentId() == null ||
                studentReportGrade.getReportId() == null) {
            result.put("code", 400);
            result.put("desc", "请求参数有误");
            return result;
        }
        if (studentReportGrade.getScore() == null ||
                studentReportGrade.getScore() < 0 || studentReportGrade.getScore() > 100) {
            result.put("code", 400);
            result.put("desc", "不合理的分数");
            return result;
        }
        studentReportGradeMapper.insert(studentReportGrade);
        result.put("code", 200);
        result.put("grade", studentReportGrade);
        return result;
    }

    @Override
    public Map<String, Object> getStudentCourseGrade(Long studentId, Long courseId) {
        Map<String, Object> result = new HashMap<>();
        if (courseId == null || studentId == null) {
            result.put("code", 400);
            result.put("desc", "请求参数有误");
            return result;
        }
        CourseSignScore courseSignScore = courseSignScoreMapper.selectById(courseId);
        if (courseSignScore == null) {
            result.put("code", 400);
            result.put("desc", "无法确定课程成绩组成");
            return result;
        }
        // 课程实验分数占比
        float experimentProportion = courseSignScore.getExperiment();
        // 课程签到分数占比
        float signProportion = courseSignScore.getSign();

        List<CourseExperimentScore> experimentList = courseExperimentScoreMapper
                .selectList(new QueryWrapper<CourseExperimentScore>()
                        .eq("course_id", courseId));
        if (experimentList == null) {
            result.put("code", 200);
            result.put("length", 0);
            return result;
        }
        result.put("code", 200);
        float amountScore = 0.0F;
        float amountProportion = 0.0f; // 总的比例
        List<Map<String, Object>> gradeList = new ArrayList<>();
        for (CourseExperimentScore experiment : experimentList) {
            amountProportion += experiment.getProportion();
            Map<String, Object> item = new HashMap<>();
            item.put("experimentId", experiment.getExperimentId());
            item.put("proportion", experiment.getProportion());

            StudentReportGrade reportGrade = studentReportGradeMapper.selectOne(
                    new QueryWrapper<StudentReportGrade>()
                            .eq("student_id", studentId)
                            .eq("course_id", courseId)
                            .eq("experiment_id", experiment.getExperimentId()));

            if (reportGrade != null) {
                item.put("grade", reportGrade.getScore());
                amountScore += reportGrade.getScore() * experiment.getProportion();
            } else {
                item.put("grade", 0);
                amountScore += 0;
            }
            gradeList.add(item);
        }
        float experimentGrade = amountScore / amountProportion;
        result.put("experimentGrade", experimentGrade);
        result.put("gradeList", gradeList);
        result.put("length", gradeList.size());

        Long signNum = courseSignMapper.selectCount(new QueryWrapper<CourseSign>()
                .eq("course_id", courseId));

        result.put("courseSignNum", signNum);
        Long signedNum = studentSignMapper.selectCount(new QueryWrapper<StudentSign>()
                .eq("course_id", courseId)
                .eq("student_id", studentId)
                .eq("sign_in", true));
        result.put("signedNum", signedNum);
        float signGrade = (float) ((signedNum + 0.0) / signNum * signProportion) * 100.0f;
        result.put("signGrade", signGrade);
        float courseGrade = (experimentGrade * experimentProportion + signGrade * signProportion) / (experimentProportion + signProportion);
        result.put("courseGrade", courseGrade);
        return result;
    }

    @Override
    public Map<String, Object> getCourseAllStudentGrade(Long courseId) {
        Map<String, Object> result = new HashMap<>();
        if (courseId == null || courseId < 0) {
            result.put("code", 400);
            result.put("desc", "请求参数有误");
        }
        result.put("code", 200);
        List<Long> studentIdList = courseClient.getCourseStudentList(courseId);
        List<Map<String, Object>> studentGradeList = new ArrayList<>();
        double amount = 0.0;
        for (Long studentId : studentIdList) {
            Map<String, Object> studentGrade = getStudentCourseGrade(studentId, courseId);
            studentGrade.put("studentId", studentId);
            studentGradeList.add(studentGrade);
            amount = amount + (double) studentGrade.get("courseGrade");
        }
        result.put("studentGradeList", studentGradeList);
        result.put("average", amount / studentIdList.size());
        return result;
    }

    @Override
    public Map<String, Object> getStudentAllCourseGrade(Long studentId) {
        Map<String, Object> result = new HashMap<>();
        List<Long> courseIdList = courseClient.getCourseListOfStudent(studentId);
        List<Map<String, Object>> courseGradeList = new ArrayList<>();

        for (Long courseId : courseIdList) {
            Map<String, Object> courseGrade = getStudentCourseGrade(studentId, courseId);
            courseGrade.put("courseId", courseId);
            courseGradeList.add(courseGrade);
        }
        result.put("code", 200);
        result.put("courseGradeList", courseGradeList);
        return result;
    }

    @Override
    public Map<String, Object> getCourseGradeProportion(Long courseId) {
        Map<String, Object> result = new HashMap<>();
        CourseSignScore courseSignScore = courseSignScoreMapper.selectById(courseId);
        if (courseSignScore == null) {
            result.put("code", 400);
            return result;
        }
        result.put("code", 200);
        result.put("courseId", courseId);
        result.put("experiment", courseSignScore.getExperiment());
        result.put("sign", courseSignScore.getSign());
        List<CourseExperimentScore> experimentScoreList = courseExperimentScoreMapper.selectList(
                new QueryWrapper<CourseExperimentScore>()
                        .eq("course_id", courseId));
        if (experimentScoreList == null) {
            result.put("code", 401);
            return result;
        }
        List<Map<String, Object>> experimentScore = new ArrayList<>();
        for (CourseExperimentScore score : experimentScoreList) {
            Map<String, Object> item = new HashMap<>();
            item.put("experimentId", score.getExperimentId());
            item.put("proportion", score.getProportion());
            experimentScore.add(item);
        }
        result.put("experimentItem", experimentScore);
        return result;
    }

    @Override
    public Map<String, Object> postCourseGradeProportion(CourseSignScore courseSignScore) {
        Map<String, Object> result = new HashMap<>();
        CourseSignScore temp = courseSignScoreMapper.selectById(courseSignScore.getCourseId());
        if (temp == null) {
            courseSignScoreMapper.insert(courseSignScore);
        } else {
            courseSignScoreMapper.updateById(courseSignScore);
        }
        result.put("code", 200);
        result.put("courseId", courseSignScore.getCourseId());
        result.put("sign", courseSignScore.getSign());
        result.put("experiment", courseSignScore.getExperiment());
        return result;
    }

    @Override
    public Map<String, Object> postCourseExperimentProportion(CourseExperimentScore courseExperimentScore) {
        Map<String, Object> result = new HashMap<>();
        CourseExperimentScore temp = courseExperimentScoreMapper.selectOne(new QueryWrapper<CourseExperimentScore>()
                .eq("course_id", courseExperimentScore.getCourseId())
                .eq("experiment_id", courseExperimentScore.getExperimentId()));
        if (temp == null) {
            courseExperimentScoreMapper.insert(courseExperimentScore);
        } else {
            courseExperimentScoreMapper.update(courseExperimentScore, new QueryWrapper<CourseExperimentScore>()
                    .eq("course_id", courseExperimentScore.getCourseId())
                    .eq("experiment_id", courseExperimentScore.getExperimentId()));
        }
        result.put("code", 200);
        result.put("courseId", courseExperimentScore.getCourseId());
        result.put("experimentId", courseExperimentScore.getExperimentId());
        result.put("proportion", courseExperimentScore.getProportion());
        return result;
    }


}
