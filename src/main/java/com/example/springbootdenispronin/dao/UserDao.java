package com.example.springbootdenispronin.dao;

import com.example.springbootdenispronin.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void create(User user);

    void update(User user);

    User getUserWithRolesById(Long id);

    void delete(Long id);

    User showByName(String name);
}
