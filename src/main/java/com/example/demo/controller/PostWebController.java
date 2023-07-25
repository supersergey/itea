package com.example.demo.controller;

import com.example.demo.controller.dto.Post;
import com.example.demo.exception.UnknownUserException;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@Controller
public class PostWebController {
    private final PostService postService;
    private final UserService userService;

    public PostWebController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/posts")
    public String getPosts(Model model, @PathVariable int userId)
    {
        try {
            var posts = postService.getPostsByUserId(userId);
            model.addAttribute("posts", posts);
        }
        catch (UnknownUserException ex)
        {
            model.addAttribute("errorMessage", "User doesn't exist!");
        }
        return "view-posts";
    }

    @PostMapping("/users/{userId}/posts")
    public String createPost(Model model, @PathVariable int userId, @RequestParam Map<String, String> body)
    {
        try {
                if (body.containsKey("title") && body.containsKey("body")) {
                    postService.save(new Post(body.get("title"), body.get("body"), userId));
            }
            var posts = postService.getPostsByUserId(userId);
            model.addAttribute("posts", posts);
        } catch (UnknownUserException ex)
        {
            model.addAttribute("errorMessage", "User doesn't exist!");
        }

        return "view-posts";
    }

}
