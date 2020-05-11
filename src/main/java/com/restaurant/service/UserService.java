package com.restaurant.service;

import com.restaurant.domain.User;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public interface UserService {
    Map<String, String> register(User user, ResourceBundle bundle);
    Optional<User> login(String username, String password);
}
