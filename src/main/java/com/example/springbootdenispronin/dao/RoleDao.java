package com.example.springbootdenispronin.dao;


import com.example.springbootdenispronin.model.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> findRolesByIds(Long[] roleIds);

    Set<Role> getAllRoles();
}
