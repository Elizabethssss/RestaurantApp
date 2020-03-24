package com.restaurant.service.validator.impl;

import com.restaurant.domain.User;
import com.restaurant.service.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_PATTERN = "(\\w+\\.?)+@(\\w+\\.?)+";

    @Override
    public Map<String, String> validate(User user) {
        final String emptyMsg = "Empty field!";
        Map<String, String> errorMessages = new HashMap<>();
        if (user.getUsername().equals("") || user.getUsername() == null) {
            errorMessages.put("usernameError", emptyMsg);
        }
        if(user.getPassword().equals("") || user.getPassword() == null) {
            errorMessages.put("passwordError", "Passwords can't be empty!");
        }
        if (user.getEmail().equals("") || user.getEmail() == null) {
            errorMessages.put("emailError", emptyMsg);
        } else if (!user.getEmail().matches(EMAIL_PATTERN)) {
            errorMessages.put("emailError", "Wrong email! Use only Aa-Zz, 0-9, ._@");
        }
        return errorMessages;
    }
}
