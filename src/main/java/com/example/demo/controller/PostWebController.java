package com.example.demo.controller;

import com.example.demo.controller.dto.Post;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PostWebController {

    private final UserService userService;
    private final PostService postService;

    public PostWebController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String getPostsByUserId(Model model) {
        var posts = postService.getPostsByUserId(0);
        model.addAttribute("posts", posts);
        return "view-posts";
    }

    @PostMapping("/posts")
    public String createUser(Model model, @RequestParam Map<String, String> body) {
        try {
            if (body.containsKey("title") && body.containsKey("body")) {
                postService.save(0, new Post(body.get("title"), body.get("body")));
            }
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", "Post with this title already exists!");
        } finally {
            model.addAttribute("posts", postService.getPostsByUserId(0));
        }
        return "view-posts";
    }
}