package com.example.springbootdenispronin.dao;

import com.example.springbootdenispronin.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> findRolesByIds(Long[] roleIds) {
        return entityManager
                .createQuery("select r from Role r where r.id in (:ids)", Role.class)
                .setParameter("ids", Arrays.asList(roleIds))
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getAllRoles() {
        return entityManager
                .createQuery("FROM Role", Role.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
