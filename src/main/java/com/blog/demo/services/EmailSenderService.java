package com.blog.demo.services;

import com.blog.demo.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

import java.util.Locale;

/**
 * Created by damiass on Oct, 2019
 */
@Service
@Slf4j
public class EmailSenderService {

    private final SpringTemplateEngine springTemplateEngine;
    private final JavaMailSender javaMailSender;
    private final static String BASE_URL = "http://localhost:8080";

    public EmailSenderService(SpringTemplateEngine springTemplateEngine,
                              JavaMailSender javaMailSender) {
        this.springTemplateEngine = springTemplateEngine;
        this.javaMailSender = javaMailSender;

    }

    // compose and send email
    @Async
    public void sendEmail(String to, String subject, String content,
                          boolean isMultiPart, boolean isHtml) {

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom("noreply@blog.admin.com");
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch(Exception e) {
            log.info("ERROR SENDING MESSAGE " + e.getMessage());
        }

    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String subject) {
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseURL", BASE_URL);
        String content = springTemplateEngine.process(templateName, context);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendActivationEmail(User user) {
        sendEmailFromTemplate(user, "email/activation", "Blog User Activation");
    }

    @Async
    public void sendResetPasswordEmail(User user) {
        sendEmailFromTemplate(user, "email/reset", "Reset Password");
    }


    @Async void sendWelcomeEmail(User user) {
        sendEmailFromTemplate(user, "email/welcome", "Welcome new Spring User");
    }

}

















