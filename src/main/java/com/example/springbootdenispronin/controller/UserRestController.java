package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> one(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserWithRolesById(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user, @RequestParam Long[] roleIds) {
        return ResponseEntity.ok().body(userService.create(user, roleIds));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> replaceEmployee(@RequestBody User user, @PathVariable Long[] roleIds) {
        return ResponseEntity.ok().body(userService.update(user, roleIds));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().build();
    }
}
