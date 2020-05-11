package com.restaurant.service.validator.impl;

import com.restaurant.domain.User;
import org.junit.Test;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserValidatorImplTest {
    private final ResourceBundle bundle = ResourceBundle.getBundle("locale", Locale.getDefault());
    private final UserValidatorImpl userValidator = new UserValidatorImpl();

    @Test
    public void validateMapShouldBeEmpty() {
        final User user = initUser("Lolitta", "lol@kiev.ua", "lolitta");
        final Map<String, String> result = userValidator.validate(user, bundle);

        assertTrue(result.isEmpty());
    }

    @Test
    public void validateWithWrongEmail() {
        final User user = initUser("Lolitta", "lolKiev.ua", "lolitta");
        final Map<String, String> result = userValidator.validate(user, bundle);

        assertTrue(result.containsKey("emailError"));
    }

    @Test
    public void validateWithEmptyFieldsMapShouldBeFull() {
        final User user = initUser("", "", "");
        final Map<String, String> result = userValidator.validate(user, bundle);

        assertEquals(3, result.size());
    }

    @Test
    public void validateWithNullFieldsMapShouldBeFull() {
        final User user = initUser(null, null, null);
        final Map<String, String> result = userValidator.validate(user, bundle);

        assertTrue(result.containsKey("usernameError"));
        assertTrue(result.containsKey("passwordError"));
        assertTrue(result.containsKey("emailError"));
    }

    private static User initUser(String username, String email, String password) {
        return User.builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .build();
    }
}