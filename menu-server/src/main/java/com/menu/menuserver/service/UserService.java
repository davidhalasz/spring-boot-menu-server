package com.menu.menuserver.service;

import com.menu.menuserver.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);
}
