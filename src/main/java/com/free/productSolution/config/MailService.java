package com.free.productSolution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEnquiryMail(String fromEmail, String name, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("yourgmail@gmail.com"); // fixed sender
        mailMessage.setTo("yourgmail@gmail.com");   // fixed recipient
        mailMessage.setSubject("New Enquiry from " + name);
        mailMessage.setText("Name: " + name + "\nEmail: " + fromEmail + "\n\nMessage:\n" + message);

        mailSender.send(mailMessage);
    }
}



