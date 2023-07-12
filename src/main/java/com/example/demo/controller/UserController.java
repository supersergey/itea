package com.example.demo.controller;

import com.example.demo.controller.dto.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
        private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public int createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("/api/users/stats")
    public int getStats() {
        return userService.count();
    }

}
