package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User create(User user, Long[] roleIds);

    User update(User user, Long[] roleIds);

    User getUserWithRolesById(Long id);

    void delete(Long id);

    User showByName(String name);
}
