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
import java.rmi.server.ExportException;
import java.util.*;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/31
 * @JDKVersion 17.0.4
 */
<<<<<<< HEAD
=======

>>>>>>> zst
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
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "role") String role,
            @RequestParam(value = "email") String email
    ) {
        try {
            Map<String, Object> personalInfo = personalInfoClient.getPersonalInfo(userId, role);
            if (Objects.equals((String) personalInfo.get("status"), "查无此人")) {
                return ResponseEntity.status(400).body("账号不存在");
            }
            if (personalInfo.get("email") != null) {
                return ResponseEntity.status(400).body("账号已经激活过，请使用账号密码登录");
            }
            MailSender.sendEmail(email, String.valueOf(userId), password, role, "verify");
            return ResponseEntity.ok("邮件发送成功");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }

    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(
            @RequestParam("code") String code
    ) {
        Base64.Decoder decoder = Base64.getDecoder();
        String str = new String(decoder.decode(code));
        String[] strings = str.split("/");
        Long userId = Long.valueOf(strings[0]);
        String password = strings[1];
        String role = strings[2];
        String email = strings[3];
        String time = strings[4];
        if (System.currentTimeMillis() - Long.parseLong(time) > 60 * 60 * 24) {
            return ResponseEntity.status(400).body("验证超时");
        }
        if (Objects.equals(role, "student")) {
            loginService.activateStudent(userId, password, email);
        } else {
            loginService.activateTeacher(userId, password, email);
        }

        return ResponseEntity.ok("激活成功");
    }

    @GetMapping("/sendForgetEmail")
    public ResponseEntity<String> forgetEmail(
            @RequestParam("userId") Long userId,
            @RequestParam("role") String role
    ) {
        try {
            Map<String, Object> personalInfo = personalInfoClient.getPersonalInfo(userId, role);
            if (Objects.equals((String) personalInfo.get("status"), "查无此人")) {
                return ResponseEntity.status(400).body("账号不存在");
            }
            if (personalInfo.get("email") == null) {
                return ResponseEntity.status(400).body("当前账号未激活");
            }
            String email = (String) personalInfo.get("email");
            String code = "";
            for (int i = 0; i < 4; i++) {
                code = code + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
            }
            MailSender.sendEmail(email, String.valueOf(userId), code, "", "forget");
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }

    }

    @GetMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(
            @RequestParam("userId") Long userId,
            @RequestParam("role") String role,
            @RequestParam("password") String password
    ) {
        try {
            if (Objects.equals(role, "student")) {
                loginService.modifyStudentPassword(userId, password);
            } else {
                loginService.modifyTeacherPassword(userId, password);
            }
            return ResponseEntity.ok("修改密码成功");
        } catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }

    }
}
