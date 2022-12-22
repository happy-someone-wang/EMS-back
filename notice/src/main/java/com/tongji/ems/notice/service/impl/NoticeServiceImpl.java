package com.tongji.ems.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import com.tongji.ems.notice.mapper.NoticeMapper;
import com.tongji.ems.notice.model.Notice;
import com.tongji.ems.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<Notice>()
                .eq("course_id", courseId)
                .last(String.format("limit %d,%d", (offset - 1) * pageSize, pageSize));
        List<Notice> noticeList = noticeMapper.selectList(queryWrapper);
        if (noticeList == null || noticeList.size() == 0) {
            result.put("status", "查询无果");
            result.put("noticeNum", 0);
        } else {
            Map<String, Object> notices = new HashMap<>();
            for (Notice notice : noticeList) {
                Map<String, Object> teacher = personalInfoClient.getPersonalInfo(notice.getTeacherId(), "teacher");
                notices.put("notice", notice);
                notices.put("teacher", teacher);
            }
            result.put("status", "查询成功");
            result.put("notices", notices);
            result.put("noticeNum", noticeList.size());
        }
        return result;
    }
}
