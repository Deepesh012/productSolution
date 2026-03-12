package com.free.productSolution.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.free.productSolution.dao.MediaRepository;
import com.free.productSolution.dao.UserRepository;
import com.free.productSolution.entities.Media;
import com.free.productSolution.entities.User;
import com.free.productSolution.entities.YoutubeVideo;
import com.free.productSolution.service.YoutubeVideoService;

@Controller
public class YoutubeController {
	
	@Autowired
	private MediaRepository mediaRepo;

    @Autowired
    private YoutubeVideoService service;
    
    @Autowired
    private UserRepository userRepository;

    

    // show the "more videos" page
    @GetMapping("/youtube/more")
    public String showMore(Model model) {
        model.addAttribute("videos", service.getAllVideos().stream().limit(7).collect(Collectors.toList()));
        return "youtube-more";
    }

    // show replace form for a given type (path variable or query param)
    @GetMapping({"/youtube/replace", "/youtube/replace/{type}"})
    public String replaceForm(@PathVariable(required=false) String type,
                              @RequestParam(required=false) String typeFromParam,
                              Model model) {
        String t = type != null ? type : typeFromParam;
        if (t == null) t = "main";
        YoutubeVideo video = service.getVideoByType(t);
        if (video == null) video = new YoutubeVideo(t, "");
        model.addAttribute("video", video);
        return "youtube-replace"; // make sure this template is in src/main/resources/templates
    }

    // submit replace request
    @PostMapping("/youtube/replace/{type}")
    public String replaceSubmit(@PathVariable String type,
                                @RequestParam("videoUrl") String videoUrl,
                                RedirectAttributes redirectAttributes) {
        service.updateVideoByType(type, videoUrl);
        redirectAttributes.addFlashAttribute("message", "✅ Video updated for " + type);
        return "redirect:/youtube/replace/" + type;
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
