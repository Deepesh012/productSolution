package com.free.productSolution.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class YoutubeVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String type; // e.g., main, office, quality-room, etc.

    private String videoUrl;

    private LocalDateTime createdAt;

    public YoutubeVideo() {
        this.createdAt = LocalDateTime.now();
    }

    public YoutubeVideo(String type, String videoUrl) {
        this.type = type;
        this.videoUrl = videoUrl;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
