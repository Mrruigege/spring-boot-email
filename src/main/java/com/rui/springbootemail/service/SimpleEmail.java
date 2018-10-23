package com.rui.springbootemail.service;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SimpleEmail {
    //发送人
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender ;

    /**
     *  发送简单的文本邮件
     * @param to 发送给人
     * @param subject 发送的主题
     * @param content 发送的内容
     */
    public void SimpleMail(String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
    }

    /**
     *  发送简单的HTML邮件
     * @param to 发送给人
     * @param subject 发送的主题
     * @param content 发送的内容
     */
    public void sendHtmlMail(String to , String subject ,String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        helper.setFrom(from);
        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @param to 发送给人
     * @param subject 发送的主题
     * @param content 发送的内容
     * @param filePath 附件地址
     * @throws MessagingException 抛出异常
     */
    public void sendAttachMail(String to , String subject , String content , String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);

        FileSystemResource file = new FileSystemResource(new File((filePath)));
        String fileName = file.getFilename();
        helper.addAttachment(fileName,file);
        System.out.println("等待发送");
        mailSender.send(message);
        System.out.println("发送成功");
    }

    public void sendInlinMail(String to,String subject,String content,String rscPath,String rscId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId,res);
        mailSender.send(message);
    }
}
