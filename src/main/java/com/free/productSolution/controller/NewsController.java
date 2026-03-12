package com.free.productSolution.controller;

import com.free.productSolution.entities.News;
import com.free.productSolution.entities.User;
import com.free.productSolution.dao.NewsRepository;
import com.free.productSolution.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	public UserRepository userRepository;
	
    @Autowired
    private NewsRepository newsRepository;

    /**
     * Serve the Manage News HTML page (from templates/manage-news.html)
     */
    @GetMapping("/manage-news")
    public String showManageNewsPage(Model model) {
        // Optional: pass all existing news to the template
        List<News> newsList = newsRepository.findAll();
        model.addAttribute("newsList", newsList);
        return "manage-news";
    }

    /**
     * REST endpoint — get all news (for index page or AJAX)
     */
    @GetMapping("/all")
    @ResponseBody
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    /**
     * REST endpoint — add new news
     */
    @PostMapping("/add")
    @ResponseBody
    public News addNews(@RequestBody News news) {
        return newsRepository.save(news);
    }

    /**
     * REST endpoint — delete news by ID
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteNews(@PathVariable Long id) {
        newsRepository.deleteById(id);
        return "News deleted successfully";
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
