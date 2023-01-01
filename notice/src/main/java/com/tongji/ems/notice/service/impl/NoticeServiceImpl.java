package com.tongji.ems.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import com.tongji.ems.notice.mapper.NoticeMapper;
import com.tongji.ems.notice.model.CourseNotice;
import com.tongji.ems.notice.service.NoticeService;
import com.tongji.ems.notice.util.GenerateIdTenth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 赵帅涛
 * @since 2022年12月22日
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private PersonalInfoClient personalInfoClient;

    @Override
    public Map<String, Object> getNoticeListByPage(Integer courseId, Integer offset, Integer pageSize) {
        if (courseId == null) {
            return null;
        }
        if (offset == null) {
            offset = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageSize < 0 || offset < 0) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<CourseNotice> queryWrapper = new QueryWrapper<CourseNotice>()
                .eq("course_id", courseId)
                .last(String.format("limit %d,%d", (offset - 1) * pageSize, pageSize));
        List<CourseNotice> noticeList = noticeMapper.selectList(queryWrapper);
        if (noticeList == null || noticeList.size() == 0) {
            result.put("status", "查询无果");
            result.put("noticeNum", 0);
        } else {
            List<Map<String, Object>> notices = new ArrayList<>();
            for (CourseNotice notice : noticeList) {
                Map<String, Object> item = new HashMap<>();
                Map<String, Object> teacher = personalInfoClient.getPersonalInfo(Long.valueOf(notice.getTeacherId()), "teacher");
                item.put("notice", notice);
                item.put("teacher", teacher);
                notices.add(item);
            }
            result.put("status", "查询成功");
            result.put("notices", notices);
            result.put("noticeNum", noticeList.size());
        }
        return result;
    }

    @Override
    public Map<String, Object> getNoticeById(Long Id) {
        if (Id == null || Id < 0 || Id > (1 << 11 - 1)) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        CourseNotice notice = noticeMapper.selectById(Id);

        if (notice == null) {
            result.put("status", "查询无果");
        } else {
            result.put("status", "查询成功");
            result.put("notice", notice);
            if (notice.getTeacherId() != null) {
                Map<String, Object> teacher = personalInfoClient.getPersonalInfo(Long.valueOf(notice.getTeacherId()), "teacher");
                if (teacher != null) {
                    result.put("teacher", teacher);
                }
            }
        }
        return result;
    }

    @Override
    public Boolean postNoticeByTeacher(CourseNotice notice) {
        if (notice.getTeacherId() == null || notice.getCourseId() == null) {
            return false;
        }
        notice.setNoticeId(GenerateIdTenth.get10UniqueId());
        notice.setCreateTime(new Date());
        noticeMapper.insert(notice);
        return true;
    }
}
