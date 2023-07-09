package com.example.demo;

import org.springframework.web.bind.annotation.*;
import com.example.demo.User;
import java.util.Random;

@RestController
public class UserController {
    private static final Random RND = new Random();

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user) {
        System.out.println(user);
        return String.valueOf(RND.nextInt());
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable int id) {
        System.out.println(id);
        return new User("Taras", "Kovalenko", 30);
    }
}
