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

    public static void sendEmail(String email, String userId, String password, String role) throws Exception {
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
        ts.connect("smtp.qq.com", "3155002905", "llpzrmzyexlxddef");
        // 创建邮件
        Message message1 = createSimpleMail(session, email, userId, password, role);

        // 发送邮件
        ts.sendMessage(message1, message1.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(Session session, String email, String userId, String password, String role)
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

        message.setContent("尊敬的" + userId + "您好！\n" + "请点击下方链接验证您的邮箱:\n" + "        http://localhost:8081/login/verifyEmail?code=" + code, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createMailWithHtml
     * @Description: 创建一封HTML邮件
     * 未用
     */
    public static MimeMessage createMailWithHtml(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("573668878@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("573668878@qq.com"));
        // 邮件的标题
        message.setSubject("JavaMail发送HTML邮件");
        // 邮件的文本内容
        message.setContent("<h1>这是一封JavaMail发送的HTML邮件！</h1>", "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createMailWithResource
     * @Description: 创建一封包含附件的邮件
     * 未用
     */
    public static MimeMessage createMailWithResource(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("573668878@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("573668878@qq.com"));
        // 邮件的标题
        message.setSubject("JavaMail发送带附件的邮件");
        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();

        // 消息
        messageBodyPart.setText("这是一封JavaMail发送的带附件的邮件！");

        // 创建多重消息
        Multipart multipart = new MimeMultipart();

        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);

        // 附件部分
        messageBodyPart = new MimeBodyPart();
        String filename = "./resource/img.jpg";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        // 发送完整消息
        message.setContent(multipart);
        return message;
    }
}
