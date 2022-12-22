package com.tongji.ems.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("personalInfo")
public interface PersonalInfoClient {
    /**
     * 获取用户信息
     *
     * @param id   用户id
     * @param role 学生(student)或教师(teacher)
     * @return 用户个人信息
     */
    @GetMapping("/person")
    Map<String, Object> getPersonalInfo(@RequestParam("id") Integer id, @RequestParam("role") String role);
}
