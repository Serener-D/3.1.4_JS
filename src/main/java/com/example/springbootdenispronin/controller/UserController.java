package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.Role;
import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.RoleService;
import com.example.springbootdenispronin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index() {
        return "user";
    }

    @GetMapping("/authenticated")
    @ResponseBody
    public ResponseEntity<User> authenticated(Principal principal) {
        return ResponseEntity.ok().body(userService.showByName(principal.getName()));
    }

    @GetMapping("/roles")
    @ResponseBody
    public ResponseEntity<Set<Role>> roles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }
}
