package com.restaurant.service;

import com.restaurant.domain.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Map<String, String> register(User user);
    Optional<User> login(String username, String password);
}
