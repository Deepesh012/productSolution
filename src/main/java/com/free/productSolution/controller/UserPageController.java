package com.free.productSolution.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.free.productSolution.config.CarouselService;
import com.free.productSolution.dao.UserRepository;
import com.free.productSolution.entities.User;

@Controller
@RequestMapping("/user")
public class UserPageController {

	private final HomeController homeController;

	@Autowired
	private UserRepository userRepository;

	UserPageController(HomeController homeController) {
		this.homeController = homeController;
	}

	// method for adding common data to response

	@ModelAttribute
	public void addCommonData(Model m, Principal principal) { // get the user by using username(Email)
		String name = principal.getName();
		User user = userRepository.getUserByUserName(name);
		System.out.println("USER :" + user);
		System.out.println("USERNAME :" + name);
		m.addAttribute("user", user);
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME :" + userName);

		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER :" + user);
		return "dashboard"; // resolves to dashboard.html
	}

//	@GetMapping("/admin")
//	public String Admindashboard(Model model, Principal principal) {
//		String userName = principal.getName();
//		System.out.println("USERNAME :" + userName);
//
//		User user = userRepository.getUserByUserName(userName);
//		System.out.println("USER :" + user);
//		model.addAttribute("user", user);
//		return "fragments/admin-dashboard"; // resolves to dashboard.html
//	}
//
//	@Autowired
//	private CarouselService carouselService;
//
//	@GetMapping("/carousel")
//	public String showCarouselPage(Model model) {
//		model.addAttribute("carouselImages", carouselService.getAllImages());
//		return "carousel-upload";
//	}
//
//	@PostMapping("/carousel/upload")
//	public String uploadCarousel(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//		try {
//			carouselService.saveImage(file);
//			redirectAttributes.addFlashAttribute("message", "✅ Image uploaded successfully!");
//		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("error", "❌ Upload failed: " + e.getMessage());
//		}
//		return "redirect:/carousel";
//	}
//
//	@PostMapping("/carousel/delete/{id}")
//	public String deleteCarousel(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//		carouselService.deleteImage(id);
//		redirectAttributes.addFlashAttribute("message", "🗑️ Image removed successfully!");
//		return "redirect:/carousel";
//	}

}
