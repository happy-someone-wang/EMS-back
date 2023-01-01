package com.tongji.ems.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.feign.clients.PersonalInfoClient;
import com.tongji.ems.notice.mapper.NoticeFileMapper;
import com.tongji.ems.notice.mapper.CourseNoticeMapper;
import com.tongji.ems.notice.mapper.SystemNoticeMapper;
import com.tongji.ems.notice.model.CourseNotice;
import com.tongji.ems.notice.model.NoticeFile;
import com.tongji.ems.notice.model.SystemNotice;
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
    private CourseNoticeMapper courseNoticeMapper;
    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    @Autowired
    private NoticeFileMapper fileMapper;

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
        List<CourseNotice> noticeList = courseNoticeMapper.selectList(queryWrapper);
        if (noticeList == null || noticeList.size() == 0) {
            result.put("code", 400);
            result.put("noticeNum", 0);
        } else {
            List<Map<String, Object>> notices = new ArrayList<>();
            for (CourseNotice notice : noticeList) {
                Map<String, Object> item = new HashMap<>();
                Map<String, Object> teacher = personalInfoClient.getPersonalInfo(notice.getTeacherId(), "teacher");
                item.put("notice", notice);
                item.put("teacher", teacher);
                notices.add(item);
            }
            result.put("code", 200);
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
        CourseNotice notice = courseNoticeMapper.selectById(Id);

        if (notice == null) {
            result.put("code", 400);
        } else {
            result.put("code", 200);
            result.put("notice", notice);
            List<NoticeFile> noticeFiles = fileMapper.selectList(new QueryWrapper<NoticeFile>()
                    .eq("notice_id", Id)
                    .eq("notice_type", "C")
                    .select("file_id", "file_name", "store_path"));
            result.put("files", noticeFiles);
            result.put("length", noticeFiles.size());
            if (notice.getTeacherId() != null) {
                Map<String, Object> teacher = personalInfoClient.getPersonalInfo(notice.getTeacherId(), "teacher");
                if (teacher != null) {
                    result.put("teacher", teacher);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> postNoticeByTeacher(CourseNotice notice) {
        if (notice.getTeacherId() == null || notice.getCourseId() == null) {
            return null;
        }
        notice.setNoticeId(GenerateIdTenth.get10UniqueId());
        notice.setCreateTime(new Date());
        courseNoticeMapper.insert(notice);
        return getNoticeById(notice.getNoticeId());
    }

    @Override
    public Map<String, Object> getSystemNoticeList(Integer offset, Integer pageSize) {
        if (offset == null) {
            offset = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (offset < 0 || pageSize < 0) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<SystemNotice> queryWrapper = new QueryWrapper<SystemNotice>()
                .select("notice_id", "title", "create_time", "top")
                .orderByDesc("create_time")
                .last(String.format("limit %d,%d", (offset - 1) * pageSize, pageSize));
        List<SystemNotice> systemNoticeList = systemNoticeMapper.selectList(queryWrapper);
        if (systemNoticeList != null) {
            result.put("code", 200);
            result.put("noticeList", systemNoticeList);
            result.put("length", systemNoticeList.size());
            return result;
        }
        result.put("code", 200);
        result.put("length", 0);
        return result;

    }

    @Override
    public Map<String, Object> getSystemNotice(Long noticeId) {
        if (noticeId == null || noticeId <= 0) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        SystemNotice systemNotice = systemNoticeMapper.selectById(noticeId);
        if (systemNotice != null) {
            List<NoticeFile> noticeFiles = fileMapper.selectList(new QueryWrapper<NoticeFile>()
                    .eq("notice_id", noticeId)
                    .eq("notice_type", "S")
                    .select("file_id", "file_name", "store_path"));
            result.put("notice", systemNotice);
            result.put("files", noticeFiles);
            result.put("code", 200);
            result.put("length", noticeFiles.size());
            return result;
        } else {
            result.put("code", 400);
            return result;
        }
    }

    @Override
    public Map<String, Object> postNoticeByAdmin(SystemNotice systemNotice) {
        if (systemNotice.getContent() != null && systemNotice.getTitle() != null) {
            systemNotice.setNoticeId(GenerateIdTenth.get10UniqueId());
            systemNotice.setCreateTime(new Date());
            systemNoticeMapper.insert(systemNotice);
            return getSystemNotice(systemNotice.getNoticeId());
        }
        return null;
    }

    @Override
    public Boolean deleteCourseNotice(Long noticeId) {
        if (noticeId != null) {
            courseNoticeMapper.deleteById(noticeId);
            return true;
        }
        return null;
    }

    @Override
    public Boolean deleteSystemNotice(Long noticeId) {
        if (noticeId != null) {
            systemNoticeMapper.deleteById(noticeId);
            return true;
        }
        return null;
    }

    @Override
    public Boolean cancelCourseTop(Long noticeId) {
        if (noticeId == null || noticeId < 0) {
            return false;
        }
        CourseNotice courseNotice = courseNoticeMapper.selectById(noticeId);
        if (courseNotice == null) {
            return false;
        }
        courseNotice.setTop(false);
        courseNoticeMapper.updateById(courseNotice);
        return true;
    }

    @Override
    public Boolean cancelSystemTop(Long noticeId) {
        if (noticeId == null || noticeId <= 0) {
            return false;
        }
        SystemNotice systemNotice = systemNoticeMapper.selectById(noticeId);
        if (systemNotice == null) {
            return false;
        }
        systemNotice.setTop(false);
        systemNoticeMapper.updateById(systemNotice);
        return true;
    }
}
