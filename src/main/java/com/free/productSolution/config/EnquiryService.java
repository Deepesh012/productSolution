package com.free.productSolution.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.free.productSolution.dao.EnquiryRepository;
import com.free.productSolution.entities.Enquiry;

@Service
public class EnquiryService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EnquiryRepository enquiryRepository;

    public String sendEnquiry(String name, String email, String message) {
        try {
            // 1️⃣ Save to database
            Enquiry enquiry = new Enquiry();
            enquiry.setName(name);
            enquiry.setEmail(email);
            enquiry.setMessage(message);
            enquiryRepository.save(enquiry);

            // 2️⃣ Send email
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("thakurdeepesh045@gmail.com");
            mail.setFrom("dotnet.support@cbsl-india.com"); // must be your SMTP email
            mail.setReplyTo(email); // reply goes to user
            mail.setSubject("New Enquiry from " + name);
            mail.setText("Name: " + name + "\nEmail: " + email + "\n\nMessage:\n" + message);

            mailSender.send(mail);

            return "Your enquiry has been sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send enquiry. Please try again.";
        }
    }
}