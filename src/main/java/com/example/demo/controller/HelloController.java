package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
         @GetMapping("/hello")
    public String HelloWorld()
    {
        return "Hello, " + "my friend!" +
                "<br>" + "How are you?";
    }
}
