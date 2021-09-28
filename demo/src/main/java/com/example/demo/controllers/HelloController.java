package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private UserService userService;

    @GetMapping("/say")
    public String hello(@RequestParam String name, @RequestParam Integer age){
        return "Hello Spring, " + name + " Boot " + age + " myN";
    }

    @PostMapping
    public String lay(@RequestBody User user){
        return user.toString();
    }
}
