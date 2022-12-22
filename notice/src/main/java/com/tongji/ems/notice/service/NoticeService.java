package com.tongji.ems.notice.service;

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


}
