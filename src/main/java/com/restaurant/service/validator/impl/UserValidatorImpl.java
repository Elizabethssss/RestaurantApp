package com.restaurant.service.validator.impl;

import com.restaurant.domain.User;
import com.restaurant.service.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_PATTERN = "(\\w+\\.?)+@(\\w+\\.?)+";

    @Override
    public Map<String, String> validate(User user, ResourceBundle bundle) {
        final String emptyMsg = bundle.getString("empty.field");
        Map<String, String> errorMessages = new HashMap<>();
        if (user.getUsername() == null || user.getUsername().equals("")) {
            errorMessages.put("usernameError", emptyMsg);
        }
        if(user.getPassword() == null || user.getPassword().equals("")) {
            errorMessages.put("passwordError", bundle.getString("empty.passwords"));
        }
        if(user.getEmail() == null || user.getEmail().equals("")) {
            errorMessages.put("emailError", emptyMsg);
        } else if (!user.getEmail().matches(EMAIL_PATTERN)) {
            errorMessages.put("emailError", bundle.getString("wrong.email"));
        }
        return errorMessages;
    }
}
