package com.free.productSolution.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.core.io.ClassPathResource;
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
import com.free.productSolution.dao.FastnersRepository;
import com.free.productSolution.entities.User;
import com.free.productSolution.entities.product.Fastners;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final FastnersRepository fastnersRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    ProductController(FastnersRepository fastnersRepository) {
        this.fastnersRepository = fastnersRepository;
    }

	@GetMapping("/add-fastners")
	//open add fastners form
	public String openAddFastnersForm(Model model) {
		
		model.addAttribute("title", "Add Fastners");
		model.addAttribute("fastners", new Fastners());
		return "products/add_fastners_form";
	}
	
	//processing add Fastners form
	@PostMapping("/process-fastners")
	public String processFastners(
	        @ModelAttribute Fastners fastners,
	        @RequestParam("image1File") MultipartFile image1,
	        @RequestParam("image2File") MultipartFile image2, RedirectAttributes redirectAttributes) {

	    
	    System.out.println("Image1: " + image1.getOriginalFilename());
	    System.out.println("Image2: " + image2.getOriginalFilename());

	    // ✅ Manually link image filenames to your entity
	    if (!image1.isEmpty()) {
	        fastners.setImage1(image1.getOriginalFilename());
	    }
	    if (!image2.isEmpty()) {
	        fastners.setImage2(image2.getOriginalFilename());
	    }

	    // ✅ (Optional) Save files to disk
	    try {
	        Path uploadDir = Paths.get("uploads/fastners");
	        Files.createDirectories(uploadDir);

	        if (!image1.isEmpty()) {
	            Path filePath1 = uploadDir.resolve(image1.getOriginalFilename());
	            image1.transferTo(filePath1);
	        }
	        if (!image2.isEmpty()) {
	            Path filePath2 = uploadDir.resolve(image2.getOriginalFilename());
	            image2.transferTo(filePath2);
	           
	        }
	        redirectAttributes.addFlashAttribute("successMessage", "Fastener added successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong while adding the fastener!");
	    }

	    System.out.println("After setting: " + fastners);
	    // Save fastners to DB (if repo exists)
	     fastnersRepository.save(fastners);

	     return "redirect:/product/add-fastners";
	}
	

	@GetMapping("/show_fastners")
	public String showFastners(Model model) {
	    model.addAttribute("fastners", fastnersRepository.findAll());
	    return "products/show_fastners"; // this should be your Thymeleaf template
	}

	@GetMapping("/view-fastner/{id}")
	public String viewFastner(@PathVariable("id") Integer id, Model model) {
	    Fastners fastner = fastnersRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid fastner ID: " + id));

	    model.addAttribute("fastner", fastner);
	    return "products/view_fastner"; // ✅ New Thymeleaf page
	}

	@GetMapping("/delete/{id}")
	public String deleteFastner(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
	    try {
	        if (fastnersRepository.existsById(id)) {
	            fastnersRepository.deleteById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Fastener deleted successfully!");
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Fastener not found!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("errorMessage", "Error while deleting fastener!");
	    }

	    return "redirect:/product/show_fastners";
	}

	
	
}
