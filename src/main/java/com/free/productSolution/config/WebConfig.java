package com.free.productSolution.config;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = Paths.get(uploadDir).toAbsolutePath().normalize().toString();

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + absolutePath + "/images/");

        registry.addResourceHandler("/videos/**")
                .addResourceLocations("file:" + absolutePath + "/videos/");
        
     // Correct carousel path
        registry.addResourceHandler("/carousel/**")
                .addResourceLocations("file:" + absolutePath + "/carousel/");
        
        // ✅ Add this line for organization chart
        registry.addResourceHandler("/organization-chart/**")
                .addResourceLocations("file:" + absolutePath + "/organization-chart/");
        
        // ✅ Add this for fasteners
        registry.addResourceHandler("/fastners/**")
                .addResourceLocations("file:" + absolutePath + "/fastners/");
        
     // Serve all /uploads/** URLs from this folder
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");

        
    }

}


