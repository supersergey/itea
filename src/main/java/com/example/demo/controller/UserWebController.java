package com.example.demo.controller;

import com.example.demo.controller.dto.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class UserWebController {

    @Autowired
    private UserService userService;

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
                var firstName = body.get("firstName");
                var lastName = body.get("lastName");
                var age =  Integer.parseInt(body.get("age"));

                userService.save(new User(firstName, lastName, age, Collections.emptyList()));
            }
        } catch (DuplicateUserException ex) {
            model.addAttribute("errorMessage", "User already exists!");
        } finally {
            model.addAttribute("users", userService.findAll());
        }
        return "view-users";
    }
}