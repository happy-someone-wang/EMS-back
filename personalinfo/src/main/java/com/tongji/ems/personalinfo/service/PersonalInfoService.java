package com.tongji.ems.personalinfo.service;

import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 新增用户信息
     *
     * @param person 学生或教师对象
     * @param role   学生(student)或教师(teacher)
     * @return 该用户的个人信息
     */
    public Map<String, Object> postPersonalInfo(Object person, String role);

    /**
     * 更新用户信息
     *
     * @param person 学生或教师对象
     * @param role   学生(student)或教师(teacher)
     * @return 用户个人信息
     */
    public Map<String, Object> updatePersonalInfo(Object person, String role);

    /**
     * 删除用户个人信息
     *
     * @param Id   用户ID
     * @param role 用户角色
     * @return 是否成功删除
     */
    public Boolean deletePersonalInfo(Integer Id, String role);

    /**
     * 修改用户头像
     *
     * @param avatar 头像文件
     * @param Id     用户ID
     * @param role   用户角色
     * @return 新头像URL
     */
    public String changeAvatar(MultipartFile avatar, Integer Id, String role);
}
