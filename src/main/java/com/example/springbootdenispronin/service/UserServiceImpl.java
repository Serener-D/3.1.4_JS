package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.dao.RoleDao;
import com.example.springbootdenispronin.dao.UserDao;
import com.example.springbootdenispronin.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userDao.getAll();
        log.info("Получен список всех пользователей");
        return users;
    }

    @Override
    @Transactional
    public void create(User user, Long[] roleIds) {
        user.setRoles(roleDao.findRolesByIds(roleIds));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(user);
        log.info("Пользователь с именем={} сохранен", user.getName());
    }

    @Override
    @Transactional
    public void update(User user, Long[] roleIds) {
        user.setRoles(roleDao.findRolesByIds(roleIds));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
        log.info("Пользователь с id={} обновлен", user.getId());
    }

    @Override
    public User getUserWithRolesById(Long id) {
        User user = userDao.getUserWithRolesById(id);
        log.info("Пользователь с id={} найден", id);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
        log.info("Пользователь с id={} удален", id);
    }

    @Override
    public User showByName(String name) {
        return userDao.showByName(name);
    }
}
