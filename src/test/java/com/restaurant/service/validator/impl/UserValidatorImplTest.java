package com.restaurant.service.validator.impl;

import com.restaurant.domain.User;
import com.restaurant.service.util.Localization;
import com.restaurant.service.util.UTF8Control;
import com.restaurant.service.validator.UserValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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