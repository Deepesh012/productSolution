package com.free.productSolution.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.free.productSolution.dao.MediaRepository;
import com.free.productSolution.dao.UserRepository;
import com.free.productSolution.entities.Media;
import com.free.productSolution.entities.User;
import com.free.productSolution.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MediaRepository mediaRepo;

	
	
    
    @RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}
    
	


	// this handler for registering user
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bresult,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed the terms and conditions..!");
				throw new Exception("You have not agreed the terms and conditions..!");
			}
			
			if (bresult.hasErrors()) {
				System.out.println("ERROR :"+bresult.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement :" +agreement);
			System.out.println("USER :" +user);
			User user2 = this.userRepository.save(user);
			
			
			model.addAttribute("user",new User());
			
			session.setAttribute("message", new Message("Successfully Register !!","alert-success"));
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went Wrong !!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
	}
	
	 @RequestMapping("/signin")
	    public String loginPage(Model model) {
	        model.addAttribute("title", "Login - Smart Contact Manager");
	        return "login";  // This should match login.html in templates
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
