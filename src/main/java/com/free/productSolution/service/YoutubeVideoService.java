package com.free.productSolution.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.free.productSolution.dao.YoutubeVideoRepository;
import com.free.productSolution.entities.YoutubeVideo;

@Service
public class YoutubeVideoService {

    @Autowired
    private YoutubeVideoRepository repo;

    // all videos sorted newest-first
    public List<YoutubeVideo> getAllVideos() {
        return repo.findAllByOrderByCreatedAtDesc();
    }

    public YoutubeVideo getVideoByType(String type) {
        return repo.findFirstByTypeOrderByCreatedAtDesc(type).orElse(null);
    }

    // Create or update by type. Returns saved entity.
    public YoutubeVideo updateVideoByType(String type, String newUrl) {
        String prepared = toEmbedOrLocalUrl(newUrl);
        Optional<YoutubeVideo> opt = repo.findFirstByTypeOrderByCreatedAtDesc(type);
        YoutubeVideo video;
        if (opt.isPresent()) {
            video = opt.get();
            video.setVideoUrl(prepared);
        } else {
            video = new YoutubeVideo(type, prepared);
        }
        return repo.save(video);
    }

    // Ensure default rows for all types exist (call from controller startup or dashboard)
    public void initializeDefaultVideos() {
        String[] types = new String[] {
            "main","office","quality_room","forging_machine","turning_machine",
            "tool_room","fg_store","sorting_area"
        };
        for (String t : types) {
            if (repo.findFirstByTypeOrderByCreatedAtDesc(t).isEmpty()) {
                repo.save(new YoutubeVideo(t, "/videos/default.mp4"));
            }
        }
    }

    // Convert common YouTube URL forms to embed form. If already starts with /videos/ (local) return as-is.
    private String toEmbedOrLocalUrl(String url) {
        if (url == null) return "/videos/default.mp4";
        url = url.trim();

        // If user provided a local path already (/videos/...), return unchanged
        if (url.startsWith("/videos/")) return url;

        // If already embed form
        if (url.contains("/embed/")) return url;

        // youtu.be shortlink
        if (url.contains("youtu.be/")) {
            String id = url.substring(url.lastIndexOf('/') + 1);
            if (id.contains("?")) id = id.substring(0, id.indexOf('?'));
            return "https://www.youtube.com/embed/" + id;
        }

        // standard watch?v=...
        if (url.contains("watch?v=")) {
            String id = url.substring(url.indexOf("v=") + 2);
            if (id.contains("&")) id = id.substring(0, id.indexOf("&"));
            return "https://www.youtube.com/embed/" + id;
        }

        // if it's a full URL but not youtube, or already embed-like, return as-is
        return url;
    }
}
