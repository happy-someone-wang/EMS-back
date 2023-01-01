package com.tongji.ems.notice.service;

import com.tongji.ems.notice.model.CourseNotice;
import com.tongji.ems.notice.model.SystemNotice;

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
    public Map<String, Object> postNoticeByTeacher(CourseNotice notice);

    /**
     * 分页获取系统通知列表，只包含标题
     *
     * @param offset   第几页
     * @param pageSize 页大小
     * @return 系统通知列表
     */
    public Map<String, Object> getSystemNoticeList(Integer offset, Integer pageSize);

    /**
     * 获取系统通知详情
     *
     * @param noticeId 系统通知ID
     * @return 系统通知对象
     */
    public Map<String, Object> getSystemNotice(Long noticeId);

    /**
     * 管理员发布系统通知
     *
     * @param systemNotice 要发布的通知
     * @return 是否发布成功
     */
    public Map<String, Object> postNoticeByAdmin(SystemNotice systemNotice);

    /**
     * 删除课程通知
     *
     * @param noticeId 课程通知ID
     * @return 是否删除成功
     */
    public Boolean deleteCourseNotice(Long noticeId);

    /**
     * 删除系统通知
     *
     * @param noticeId 系统通知ID
     * @return 是否删除成功
     */
    public Boolean deleteSystemNotice(Long noticeId);

    /**
     * 课程通知取消置顶
     *
     * @param noticeId 通知ID
     * @return 是否成功取消
     */
    public Boolean cancelCourseTop(Long noticeId);

    /**
     * 取消系统通知置顶
     *
     * @param noticeId 通知ID
     * @return 是否成功取消
     */
    public Boolean cancelSystemTop(Long noticeId);
}
