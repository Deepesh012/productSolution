package com.free.productSolution.controller;

import java.nio.file.*;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.free.productSolution.dao.MediaRepository;
import com.free.productSolution.dao.UserRepository;
import com.free.productSolution.entities.Media;
import com.free.productSolution.entities.User;

@Controller
public class MediaController {

	@Autowired
	public UserRepository userRepository;
	
    @Autowired
    private MediaRepository mediaRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

 // ✅ Open upload page for a specific banner
    @GetMapping("/media")
    public String showUploadForm(@RequestParam("type") String type, Model model) {
        Media existingMedia = mediaRepo.findTopByTypeOrderByCreatedAtDesc(type);
        if (existingMedia == null) {
            existingMedia = new Media();
            existingMedia.setType(type);
        }

        model.addAttribute("existingMedia", existingMedia); // ✅ renamed for HTML binding
        model.addAttribute("type", type);
        return "media-upload"; // thymeleaf form with TinyMCE
    }


 // ✅ Handle file upload + heading + description update
    @PostMapping("/media/upload")
    public String uploadBanner(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam("type") String type,
                               @RequestParam(value = "heading", required = false) String heading,
                               @RequestParam(value = "description", required = false) String description,
                               RedirectAttributes redirectAttributes) {
        try {
            Media existingMedia = mediaRepo.findTopByTypeOrderByCreatedAtDesc(type);
            if (existingMedia == null) {
                existingMedia = new Media();
                existingMedia.setType(type);
            }

            // ✅ Handle image file upload if provided
            if (file != null && !file.isEmpty()) {
                Path uploadPath = Paths.get(uploadDir, "images").toAbsolutePath().normalize();
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFileName = file.getOriginalFilename();
                Path filePath = uploadPath.resolve(originalFileName);
                file.transferTo(filePath.toFile());

                existingMedia.setImageUrl("/images/" + originalFileName);
            }

            // ✅ Save or update heading + description
            existingMedia.setDescription(description);
            existingMedia.setType(type); // just to be safe

            // ✅ new field: heading
            if (heading != null) {
                existingMedia.setHeading(heading);
            }

            mediaRepo.save(existingMedia);
            redirectAttributes.addFlashAttribute("message", "✅ " + type + " updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "❌ Update failed for " + type);
        }

        return "redirect:/media?type=" + type;
    }



 // ✅ Optional: Display all banners for testing
    @GetMapping("/banners")
    public String getBanners(Model model) {
        model.addAttribute("banners", mediaRepo.findAll());
        return "banners";
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
