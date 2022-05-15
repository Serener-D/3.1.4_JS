package com.example.springbootdenispronin.dao;

import com.example.springbootdenispronin.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserWithRolesById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getUserWithRolesById(id));
    }

    @Override
    public User showByName(String name) {
        return entityManager
                .createQuery("select u from User u join fetch u.roles r where u.name =?1 ", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }

}
