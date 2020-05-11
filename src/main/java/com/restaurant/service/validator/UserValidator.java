package com.restaurant.service.validator;

import com.restaurant.domain.User;

import java.util.Map;
import java.util.ResourceBundle;

public interface UserValidator {
    Map<String, String> validate(User user, ResourceBundle bundle);
}
