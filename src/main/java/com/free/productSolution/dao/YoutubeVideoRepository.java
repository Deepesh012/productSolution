package com.free.productSolution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.free.productSolution.entities.YoutubeVideo;
import java.util.Optional;
import java.util.List;

public interface YoutubeVideoRepository extends JpaRepository<YoutubeVideo, Long> {
    Optional<YoutubeVideo> findFirstByTypeOrderByCreatedAtDesc(String type);
    List<YoutubeVideo> findAllByOrderByCreatedAtDesc();
}
