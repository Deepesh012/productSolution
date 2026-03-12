package com.free.productSolution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.free.productSolution.entities.Media;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findTopByOrderByCreatedAtDesc();
    Media findTopByDescriptionOrderByCreatedAtDesc(String description); // ✅ new
    Media findTopByTypeOrderByCreatedAtDesc(String type);
    List<Media> findTop6ByTypeOrderByCreatedAtDesc(String type);
    List<Media> findByTypeOrderByCreatedAtAsc(String type);



}

