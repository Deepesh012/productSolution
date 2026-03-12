package com.free.productSolution.dao;

import com.free.productSolution.entities.CarouselImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarouselImageRepository extends JpaRepository<CarouselImage, Long> {

    // Get all images ordered by creation time (oldest first)
    List<CarouselImage> findAllByOrderByCreatedAtAsc();
    
    // Optional: get first image (oldest) to delete when max reached
    CarouselImage findFirstByOrderByCreatedAtAsc();
    
    List<CarouselImage> findAllByOrderByCreatedAtDesc();
}