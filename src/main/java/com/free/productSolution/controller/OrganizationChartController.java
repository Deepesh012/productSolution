package com.free.productSolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.free.productSolution.dao.UserRepository;
import com.free.productSolution.entities.User;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.Comparator;

@Controller
public class OrganizationChartController {

    private static final String UPLOAD_DIR = "uploads/organization-chart/";
    
    @Autowired
    public UserRepository userRepository;

    // ✅ GET — show upload form
    @GetMapping("/organization-chart/upload")
    public String showUploadForm(Model model) {
        // Add currently available organization chart name (if any)
        File folder = new File(UPLOAD_DIR);
        File[] files = folder.exists() ? folder.listFiles((dir, name) -> name.endsWith(".pdf")) : new File[0];

        if (files != null && files.length > 0) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
            model.addAttribute("currentChart", files[0].getName());
        } else {
            model.addAttribute("currentChart", null);
        }

        return "organization_chart_upload"; // your Thymeleaf template
    }

    // ✅ POST — handle file upload
    @PostMapping("/organization/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "⚠️ Please select a file to upload.");
            return "redirect:/organization-chart/upload";
        }

        try {
            // Create upload directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Delete old charts so only latest remains
            for (File existingFile : uploadDir.listFiles()) {
                existingFile.delete();
            }

            // Save the new uploaded chart
            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            redirectAttributes.addFlashAttribute("message",
                    "✅ File uploaded successfully: " + file.getOriginalFilename());

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "❌ File upload failed!");
        }

        return "redirect:/organization-chart/upload";
    }

    // ✅ GET — Always return the latest organization chart
    @GetMapping("/organization-chart/latest")
    public void getLatestOrganizationChart(HttpServletResponse response) throws IOException {
        File folder = new File(UPLOAD_DIR);

        if (!folder.exists() || folder.listFiles() == null || folder.listFiles().length == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("No organization chart available.");
            return;
        }

        // Pick the most recent file
        File latestFile = Arrays.stream(folder.listFiles((dir, name) -> name.endsWith(".pdf")))
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        if (latestFile == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("No organization chart found.");
            return;
        }

        response.setContentType("application/pdf");
        Files.copy(latestFile.toPath(), response.getOutputStream());
        response.getOutputStream().flush();
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
