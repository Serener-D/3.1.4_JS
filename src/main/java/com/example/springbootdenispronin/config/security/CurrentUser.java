package com.example.springbootdenispronin.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final int age;

    public CurrentUser(String username, String password, int age, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
