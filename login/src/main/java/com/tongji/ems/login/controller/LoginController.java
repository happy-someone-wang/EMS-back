package com.tongji.ems.login.controller;

import com.tongji.ems.feign.clients.PersonalInfoClient;
import com.tongji.ems.login.model.Student;
import com.tongji.ems.login.model.Teacher;
import com.tongji.ems.login.service.LoginService;
import com.tongji.ems.login.tools.JwtUtil;
import com.tongji.ems.login.tools.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    PersonalInfoClient personalInfoClient;


    @GetMapping("/userLogin")
    public ResponseEntity<Map<String, Object>> userLogin(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "role") String role
    ) {
        try {
            Map<String, Object> result = new HashMap<>();
            if (Objects.equals(role, "student")) {
                Student student = loginService.getStudentById(userId);
                if (student == null) {
                    result.put("status", "id error");
                } else if (!Objects.equals(student.getPassword(), password)) {
                    result.put("status", "password error");
                } else {
                    result.put("status", "success");
                    result.put("token", JwtUtil.sign(String.valueOf(userId), role));
                }
                return ResponseEntity.ok(result);
            } else if (Objects.equals(role, "teacher")) {
                Teacher teacher = loginService.getTeacherById(userId);
                if (teacher == null) {
                    result.put("status", "id error");
                } else if (!Objects.equals(teacher.getPassword(), password)) {
                    result.put("status", "password error");
                } else {
                    result.put("status", "success");
                    result.put("token", JwtUtil.sign(String.valueOf(userId), role));
                }
                return ResponseEntity.ok(result);
            } else {
                if (userId == 1) {
                    result.put("status", "success");
                    result.put("token", JwtUtil.sign(String.valueOf(userId), role));
                } else {
                    result.put("status", "error");
                }
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/userLogout")
    public ResponseEntity<String> userLogout() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(
            @RequestParam(value = "token") String token
    ) {
        try {
            Long userid = Long.valueOf(Objects.requireNonNull(JwtUtil.getUserId(token)));
            String role = JwtUtil.getRole(token);

            // 管理员 直接返回
            if (Objects.equals(role, "admin")) {
                Map<String, Object> result = new HashMap<>();
                result.put("role", role);
                result.put("userId", userid);
                return ResponseEntity.ok(result);
            }
            Map<String, Object> personalInfo = personalInfoClient.getPersonalInfo(userid, role);
            personalInfo.put("role", role);
            personalInfo.put("userId", userid);
            return ResponseEntity.ok(personalInfo);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/activateAccount")
    public ResponseEntity<String> activateAccount(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "role") String role,
            @RequestParam(value = "email") String email
    ) {
        try {
            MailSender.sendEmail(email, userId, password, role);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }

    }

}
