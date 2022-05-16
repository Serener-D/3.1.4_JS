package com.example.springbootdenispronin.model.assembler;

import com.example.springbootdenispronin.controller.UserRestController;
import com.example.springbootdenispronin.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                WebMvcLinkBuilder.linkTo(methodOn(UserRestController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserRestController.class).all()).withRel("users"));
    }

    public CollectionModel<EntityModel<User>> toCollectionModel(List<? extends User> entities) {
        List<EntityModel<User>> users = entities.stream()
                .map(this::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserRestController.class).all()).withSelfRel());
    }

}


