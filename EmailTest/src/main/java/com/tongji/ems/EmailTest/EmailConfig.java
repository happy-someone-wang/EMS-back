package com.tongji.ems.EmailTest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class EmailConfig {
    /**
     * 发送邮件服务器
     */
    @Value("${spring.mail.host}")
    private String host;
    /**
     * stmp的端口
     */
    @Value("${spring.mail.port}")
    private Integer port;
    /**
     * 发送邮件地址，和user一样
     */
    @Value("${spring.mail.username}")
    private String from;
    /**
     * 发送邮件的邮箱地址
     */
    @Value("${spring.mail.username}")
    private String user;
    /**
     * 客户端授权码，非邮箱密码
     */
    @Value("${spring.mail.pass}")
    private String pass;

}
