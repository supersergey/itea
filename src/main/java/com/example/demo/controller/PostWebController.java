package com.example.demo.controller;

import com.example.demo.controller.dto.Post;
import com.example.demo.controller.dto.SortOrder;
import com.example.demo.exception.BlankStringException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class PostWebController {
    private final PostService postService;
    public PostWebController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/users/{id}/posts")
    public String getPostsByUserId(
            Model model,
            @PathVariable int id,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "DESC") SortOrder sort

    ) {
        try {
            var posts = postService.getPostsByUserId(id, limit, sort);
            model.addAttribute("posts", posts);
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", "User not found");
        }
        return "view-user-posts";
    }

    @PostMapping("/users/{id}/posts")
    public String createPost(
            Model model,
            @PathVariable int id,
            @RequestParam Map<String, String> body
    ) {
        try {
            if (body.containsKey("postTitle") && body.containsKey("postBody")) {
                try {
                    postService.save(id, new Post(body.get("postTitle"), body.get("postBody")));
                } catch (BlankStringException e) {
                    model.addAttribute("emptyFieldsErrorMessage", "Fields for the post message must not be empty");
                }
                var posts = postService.getPostsByUserId(id, Integer.MAX_VALUE, SortOrder.DESC);
                model.addAttribute("posts", posts);
            }
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", "User not found");
        }
        return "view-user-posts";
    }

}
