package com.example.demo.controller;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public User saveUser(@RequestBody User user) {
        try {
            int userId = userService.save(user);
            System.out.println("User " + user + " was saved successfully with id " + userId);
        } catch (DuplicateUserException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable int userId) {
        try {
            return userService.findById(userId);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @PatchMapping("/api/users/update/{userId}")
    public User updateUserById(@PathVariable int userId, @RequestBody User user) {
        try {
            userService.update(userId, user);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    @DeleteMapping("/api/users/delete/{userId}")
    public void deletePostById(@PathVariable int userId) {
        try {
            userService.deleteById(userId);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

}