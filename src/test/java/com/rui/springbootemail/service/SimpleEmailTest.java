package com.rui.springbootemail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import java.io.File;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleEmailTest {


    @Resource
    SimpleEmail simpleEmail;
    @Test
    public void simpleMail() throws Exception {
        simpleEmail.SimpleMail("1060401583@qq.com","my first email","my first email");
    }
    @Test
    public void sendHtmlMail() throws Exception {
        String content = "<html> \n" + "<body>\n" +
                "<h3> 你好 </h3>" +
                "</body>\n" +
                "</html>";
        simpleEmail.sendHtmlMail("191868314@qq.com","html email",content);
    }
    @Test
    public void sendAttachMail() throws MessagingException {
        String filePath="C:/Users/asus/Pictures/PPT/800.jpg";
        simpleEmail.sendAttachMail("2516224139@qq.com","块回来点赞","块回来点赞",filePath);
    }
    @Test
    public void sendInlinMail() throws MessagingException {
        String resPath = "C:/Users/asus/Pictures/PPT/800.jpg";
        String resId = "zz1";
        String content = "<html>\n" + "<body>\n" +
                "<img src=\'cid:" + resId + "\'>" + "</img>" +
                "</body>\n" +
                "</html>";
        simpleEmail.sendInlinMail("191868314@qq.com","hello",content,resPath,resId);
    }
}