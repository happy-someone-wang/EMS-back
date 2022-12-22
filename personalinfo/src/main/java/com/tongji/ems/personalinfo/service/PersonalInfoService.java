package com.tongji.ems.personalinfo.service;

import java.util.Map;

/**
 * @author 赵帅涛
 * @since 2022年12月22日
 */
public interface PersonalInfoService {
    /**
     * 获取学生或教师的个人信息
     *
     * @param id   用户ID
     * @param role 学生(student)或教师(teacher),其它返回空
     */
    public Map<String, Object> getPersonalInfo(Integer id, String role);
}
