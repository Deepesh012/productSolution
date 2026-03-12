package com.free.productSolution.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;    // e.g. /images/banner1.jpg
    private String type;        // banner1, banner2, banner3
    private String heading;     // e.g. "Product Solutions"
    
    @Column(columnDefinition = "TEXT")
    private String description; // long text from TinyMCE editor

    private LocalDateTime createdAt = LocalDateTime.now();

    public Media() {}

    public Media(String imageUrl, String type) {
        this.imageUrl = imageUrl;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getHeading() { return heading; }
    public void setHeading(String heading) { this.heading = heading; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
