package com.example.springbootdenispronin.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@NoArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String index() {
        return "admin";
    }
}
