package com.free.productSolution.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.free.productSolution.config.CarouselService;
import com.free.productSolution.dao.MediaRepository;
import com.free.productSolution.entities.CarouselImage;
import com.free.productSolution.entities.Media;
import com.free.productSolution.entities.YoutubeVideo;
import com.free.productSolution.service.YoutubeVideoService;

@Controller
public class HomeController {

    @Autowired
    private MediaRepository mediaRepo;

    @Autowired
    private YoutubeVideoService youtubeService;
    
    @Autowired
    private CarouselService carouselService;


    @GetMapping({"/", "/index"})
    public String home(Model model) {

        // --- Youtube videos ---
        youtubeService.initializeDefaultVideos();
        YoutubeVideo mainVideo = youtubeService.getVideoByType("main");
        model.addAttribute("video", mainVideo);

        List<YoutubeVideo> moreVideos = youtubeService.getAllVideos()
                .stream()
                .limit(7)
                .collect(Collectors.toList());
        model.addAttribute("videos", moreVideos);

        // --- Banners ---
        Media banner1 = mediaRepo.findTopByTypeOrderByCreatedAtDesc("banner1");
        Media banner2 = mediaRepo.findTopByTypeOrderByCreatedAtDesc("banner2");
        Media banner3 = mediaRepo.findTopByTypeOrderByCreatedAtDesc("banner3");

        // Safely add banners (check for null)
        if (banner1 != null) model.addAttribute("banner1", banner1);
        if (banner2 != null) model.addAttribute("banner2", banner2);
        if (banner3 != null) model.addAttribute("banner3", banner3);
        
     // --- Carousel images ---
        List<CarouselImage> carouselImages = carouselService.getAllImages();
        System.out.println("Carousel Images: " + carouselImages.size());
        model.addAttribute("carouselImages", carouselImages);


        

        return "index";
    }
}
