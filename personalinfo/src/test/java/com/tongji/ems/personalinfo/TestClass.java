package com.tongji.ems.personalinfo;

import com.tongji.ems.personalinfo.service.PersonalInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestClass {
    @Autowired
    private PersonalInfoService personalInfoService;

    @Test
    public void test01() {
        Object res = personalInfoService.getPersonalInfo(2050747, "student");
        System.out.println(res);
    }
}
