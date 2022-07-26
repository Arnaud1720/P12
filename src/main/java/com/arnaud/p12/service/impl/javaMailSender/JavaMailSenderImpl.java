package com.arnaud.p12.service.impl.javaMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailSenderImpl {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String subject,String body)
    {

        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("derisbourgarnaud@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Email envoyer avec succes!");
    }

}
