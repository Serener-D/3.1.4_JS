package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.RoleService;
import com.example.springbootdenispronin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("userToAdd", new User());
        model.addAttribute("admin", userService.showByName(principal.getName()));
        return "admin";
    }

    @PostMapping
    public String create(@Valid User user, BindingResult bindingResult, @RequestParam Long[] roleIds) {

        if (bindingResult.hasErrors()) {
            log.error("Ошибка в запросе");
            return "redirect:/admin";
        }

        userService.create(user, roleIds);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PatchMapping
    public String update(@Valid User user, BindingResult bindingResult, @RequestParam Long[] roleIds) {

        if (bindingResult.hasErrors()) {
            log.error("Ошибка в запросе");
            return "redirect:/admin";
        }

        userService.update(user, roleIds);
        return "redirect:/admin";
    }

}
