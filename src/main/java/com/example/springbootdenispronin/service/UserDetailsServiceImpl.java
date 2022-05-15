package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.dao.UserDao;
import com.example.springbootdenispronin.config.security.CurrentUser;
import com.example.springbootdenispronin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userDao.showByName(name);
        // тут, на самом деле, можно было обычного юзера-Entity отдать
        return new CurrentUser(user.getUsername(), user.getPassword(), user.getAge(), user.getAuthorities());
    }
}
