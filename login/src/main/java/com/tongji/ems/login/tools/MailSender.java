package com.tongji.ems.login.tools;

import com.sun.mail.util.MailSSLSocketFactory;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @Author 2051196 刘一飞
 * @Date 2023/1/1
 * @JDKVersion 17.0.4
 */
public class MailSender {

    public static void sendEmail(String email, String userId, String password, String role, String type) throws Exception {
        //创建Properties对象
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名为QQ邮箱的服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 设置发送邮件协议名称为SMTP(Simple Mail Transfer Protocol)
        prop.setProperty("mail.transport.protocol", "smtp");

        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        ts.connect("smtp.qq.com", "3155002905", "junyuxuudpoadfbe");
        // 创建邮件
        Message message;
        if (Objects.equals(type, "verify")) {
            message = createVerifyMail(session, email, userId, password, role);
        } else {
            message = createForgetMail(session, email, userId, password);
        }


        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createVerifyMail
     * @Description: 创建验证邮件
     */
    public static MimeMessage createVerifyMail(Session session, String email, String userId, String password, String role)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        /*如果你想发送一封e-mail给多个收件人，那么使用下面的方法来指定多个收件人ID：
            void addRecipients(Message.RecipientType type,
                   Address[] addresses) throws MessagingException
          type:要被设置为 TO, CC 或者 BCC，这里 CC 代表抄送、BCC 代表秘密抄送。
          举例：Message.RecipientType.TO
          addresses: 这是 email ID 的数组。在指定电子邮件 ID 时，你将需要使用 InternetAddress() 方法。
        */
        message.setFrom(new InternetAddress("3155002905@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 邮件的标题
        message.setSubject("实验教学管理系统EMS:请验证您的邮箱");
        // 邮件的文本内容

        Base64.Encoder encoder = Base64.getEncoder();
        String str = userId + "/" + password + "/" + role + "/" + email + "/" + System.currentTimeMillis();
        String code = encoder.encodeToString(str.getBytes());

        message.setContent("尊敬的" + userId + "您好！\n" + "请点击下方链接验证您的邮箱:\n" + "        http://123.60.156.14:7999/login/verifyEmail?code=" + code, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createForgetMail
     * @Description: 创建忘记密码的邮件
     */
    public static MimeMessage createForgetMail(Session session, String email, String userId, String code)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        /*如果你想发送一封e-mail给多个收件人，那么使用下面的方法来指定多个收件人ID：
            void addRecipients(Message.RecipientType type,
                   Address[] addresses) throws MessagingException
          type:要被设置为 TO, CC 或者 BCC，这里 CC 代表抄送、BCC 代表秘密抄送。
          举例：Message.RecipientType.TO
          addresses: 这是 email ID 的数组。在指定电子邮件 ID 时，你将需要使用 InternetAddress() 方法。
        */
        message.setFrom(new InternetAddress("3155002905@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 邮件的标题
        message.setSubject("实验教学管理系统EMS:重置密码验证邮件");
        // 邮件的文本内容
        message.setContent("尊敬的" + userId + "您好！\n"
                + "您正在尝试重置您的密码，请输入下面的验证码来进行验证\n" +
                "\t\t\t" + code, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
