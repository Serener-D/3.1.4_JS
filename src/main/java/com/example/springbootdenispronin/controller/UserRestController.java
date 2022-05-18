package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.model.assembler.UserModelAssembler;
import com.example.springbootdenispronin.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    //UserModelAssembler adds links to json response
    private final UserModelAssembler assembler;

    public UserRestController(UserService userService, UserModelAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> all() {

        List<User> users = userService.getAll();

        return assembler.toCollectionModel(users);
    }

    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable Long id) {

        User user = userService.getUserWithRolesById(id);

        return assembler.toModel(user);
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody User user, @RequestParam Long[] roleIds) {

        EntityModel<User> entityModel = assembler.toModel(userService.create(user, roleIds));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody User user, @PathVariable Long[] id) {

        User updatedEmployee = userService.update(user, id);

        EntityModel<User> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
