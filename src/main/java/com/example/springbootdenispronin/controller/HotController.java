package com.example.springbootdenispronin.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@NoArgsConstructor
@RequestMapping("/hot")
public class HotController {

    @GetMapping
    public String get() {
        return "hot";
    }
}
