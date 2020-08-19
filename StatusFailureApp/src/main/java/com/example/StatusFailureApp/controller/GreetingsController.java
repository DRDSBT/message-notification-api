package com.example.StatusFailureApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
