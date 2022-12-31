package com.tongji.ems.notice.service;

import com.tongji.ems.notice.model.CourseNotice;
import java.util.Map;

public interface NoticeService {

    /**
     * 分页获取通知列表
     *
     * @param courseId 课程ID
     * @param offset   偏移量-第几页
     * @param pageSize 每页大小
     * @return 通知列表
     */
    public Map<String, Object> getNoticeListByPage(Integer courseId, Integer offset, Integer pageSize);

    /**
     * 根据通知ID获取具体的通知信息
     *
     * @param Id 通知ID
     * @return 通知实体
     */
    public Map<String, Object> getNoticeById(Long Id);

    /**
     * 教师发布通知
     *
     * @param notice 要发布的通知
     * @return 是否成功发布
     */
    public Boolean postNoticeByTeacher(CourseNotice notice);
}
