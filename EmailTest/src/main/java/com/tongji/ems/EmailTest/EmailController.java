package com.tongji.ems.EmailTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("emails")
public class EmailController{
    @Autowired
    private EmailConfig emailConfig;

    @GetMapping("send")
    public String send(@RequestParam String email, @RequestParam String code) {
        boolean result = sendEmail(email, code);
        return result ? "ok" : "error";
    }

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String email, String code) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailConfig.getFrom());

        message.setTo(email);

        message.setSubject("账号激活邮件");

        message.setText("您的验证码是：" + code + "。有效期10分钟，请尽快输入验证");

        try {
            javaMailSender.send(message);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

}
