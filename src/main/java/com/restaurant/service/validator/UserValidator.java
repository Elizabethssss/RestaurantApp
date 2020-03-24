package com.restaurant.service.validator;

import com.restaurant.domain.User;

import java.util.Map;

public interface UserValidator {
    Map<String, String> validate(User user);
}
