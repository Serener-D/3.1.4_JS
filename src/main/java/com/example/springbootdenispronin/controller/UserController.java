package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index() {
        return "user";
    }

    @GetMapping("/json")
    @ResponseBody
    public EntityModel<User> edit(Principal principal) {
        User user = userService.showByName(principal.getName());

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).edit(principal)).withSelfRel());

    }
}
