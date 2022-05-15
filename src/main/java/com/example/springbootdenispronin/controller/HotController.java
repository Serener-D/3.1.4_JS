package com.example.springbootdenispronin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/hot")
public class HotController {

    @GetMapping
    public String get() {
        log.info("SOOO HOT!");
        return "hot";
    }
}
