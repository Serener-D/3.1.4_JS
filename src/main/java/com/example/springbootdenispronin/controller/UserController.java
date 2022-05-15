package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        User user = userService.showByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
