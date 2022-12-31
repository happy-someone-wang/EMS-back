package com.tongji.ems.login.controller;

import com.tongji.ems.login.model.Student;
import com.tongji.ems.login.model.Teacher;
import com.tongji.ems.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/userLogin")
    public ResponseEntity<String> getExperimentList(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "role") String role
    ) {
        try {
            if (Objects.equals(role, "student")) {
                Student student = loginService.getStudentById(userId);
                if (student == null) {
                    return ResponseEntity.status(400).body("账号错误");
                } else if (!Objects.equals(student.getPassword(), password)) {
                    return ResponseEntity.status(400).body("密码错误");
                } else {
                    return ResponseEntity.ok("登录成功");
                }

            } else {
                Teacher teacher = loginService.getTeacherById(userId);
                if (teacher == null) {
                    return ResponseEntity.status(400).body("账号错误");
                } else if (!Objects.equals(teacher.getPassword(), password)) {
                    return ResponseEntity.status(400).body("密码错误");
                } else {
                    return ResponseEntity.ok("登录成功");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


}
