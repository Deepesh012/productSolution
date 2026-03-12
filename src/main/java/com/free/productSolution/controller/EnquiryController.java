package com.free.productSolution.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.free.productSolution.config.EnquiryService;
import com.free.productSolution.dao.UserRepository;
import com.free.productSolution.entities.EnquiryForm;
import com.free.productSolution.entities.User;

@RestController
@RequestMapping("/api")

public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/enquiry")
    public String sendEnquiry(@RequestBody EnquiryForm form) {
        return enquiryService.sendEnquiry(form.getName(), form.getEmail(), form.getMessage());
    }
    
    @ModelAttribute
    public void addLoggedInUser(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByEmail(username);
            model.addAttribute("user", user);
        }
    }
}