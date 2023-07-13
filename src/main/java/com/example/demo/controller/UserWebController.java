package com.example.demo.controller;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserWebController {
    private final UserService userService;

    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        var users = userService.findAll();
        model.addAttribute("users", users);
        return "view-users";
    }

    @PostMapping("/users")
    public String createUser(Model model, @RequestParam Map<String, String> body) {
        try {
            if (body.containsKey("firstName") && body.containsKey("lastName")) {
                userService.save(new User(body.get("firstName"), body.get("lastName")));
            }
        } catch (DuplicateUserException ex) {
            model.addAttribute("errorMessage", "User already exists!");
        } finally {
            model.addAttribute("users", userService.findAll());
        }
        return "view-users";
    }
}
