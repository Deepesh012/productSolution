package com.free.productSolution.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.free.productSolution.dao.CarouselImageRepository;
import com.free.productSolution.dao.MediaRepository;
import com.free.productSolution.entities.CarouselImage;
import com.free.productSolution.entities.Media;

@Service
public class CarouselService {
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Autowired
    private CarouselImageRepository carouselRepo;


    @Autowired
    private MediaRepository mediaRepo;

    private static final int MAX_CAROUSEL_IMAGES = 8;

    // ✅ Get only carousel images (latest 6)
    public List<CarouselImage> getAllImages() {
        return carouselRepo.findAllByOrderByCreatedAtDesc();
    }

    // ✅ Add new carousel image, delete oldest if limit reached
    public void addImage(Media image) {
        List<Media> images = mediaRepo.findByTypeOrderByCreatedAtAsc("carousel");
        if (images.size() >= MAX_CAROUSEL_IMAGES) {
            Media oldest = images.get(0);
            mediaRepo.delete(oldest);
        }
        mediaRepo.save(image);
    }
    
    public void saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IOException("No file selected");

        Path uploadPath = Paths.get(uploadDir, "carousel").toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());

        CarouselImage image = new CarouselImage();
        image.setImageUrl("/carousel/" + fileName);

        List<CarouselImage> images = carouselRepo.findAllByOrderByCreatedAtAsc();
        if (images.size() >= MAX_CAROUSEL_IMAGES) {
            carouselRepo.delete(images.get(0)); // remove oldest
        }

        carouselRepo.save(image);
    }

    public void deleteImage(Long id) {
        carouselRepo.deleteById(id);
    }
}
