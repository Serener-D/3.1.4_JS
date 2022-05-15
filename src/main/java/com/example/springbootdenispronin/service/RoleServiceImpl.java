package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.dao.RoleDao;
import com.example.springbootdenispronin.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getAllRoles() {
        Set<Role> allRoles = roleDao.getAllRoles();
        log.info("Получен список всех пользователей");
        return allRoles;
    }
}
