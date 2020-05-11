package com.restaurant.service.impl;

import com.restaurant.dao.UserDao;
import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.entity.UserEntity;
import com.restaurant.service.mapper.Mapper;
import com.restaurant.service.validator.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    public static final User USER = initUser();
    public static final UserEntity USER_ENTITY = initUserEntity();
    public static Map<String, String> ERROR_MAP;

    @Mock
    private UserDao userDao;
    @Mock
    private UserValidator userValidator;
    @Mock
    private Mapper<UserEntity, User> userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        ERROR_MAP = new HashMap<>();
    }

    @Test
    public void registerShouldReturnEmptyMap() {
        when(userValidator.validate(any(), eq(RESOURCE_BUNDLE))).thenReturn(ERROR_MAP);
        when(userDao.findByEmail(USER.getEmail())).thenReturn(Optional.empty());
        when(userMapper.mapDomainToEntity(USER)).thenReturn(USER_ENTITY);

        Map<String, String> errorsMap = userService.register(USER, RESOURCE_BUNDLE);

        assertTrue(errorsMap.isEmpty());
        verify(userValidator).validate(any(), eq(RESOURCE_BUNDLE));
        verify(userDao).findByEmail(USER.getEmail());
        verify(userMapper).mapDomainToEntity(USER);
    }

    @Test
    public void registerShouldReturnErrorsMap() {
        ERROR_MAP.put("Error", "Error");
        when(userValidator.validate(any(), eq(RESOURCE_BUNDLE))).thenReturn(ERROR_MAP);
        when(userDao.findByEmail(USER.getEmail())).thenReturn(Optional.of(USER_ENTITY));

        Map<String, String> errorsMap = userService.register(USER, RESOURCE_BUNDLE);

        assertFalse(errorsMap.isEmpty());
        verify(userValidator).validate(any(), eq(RESOURCE_BUNDLE));
        verify(userDao).findByEmail(USER.getEmail());
    }

    @Test
    public void loginShouldReturnOptionalUser() {
        when(userDao.findByEmail(USER.getEmail())).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapEntityToDomain(USER_ENTITY)).thenReturn(USER);

        Optional<User> user = userService.login(USER.getEmail(), USER.getPassword());

        assertEquals(Optional.of(USER), user);
        verify(userDao).findByEmail(USER.getEmail());
        verify(userMapper).mapEntityToDomain(USER_ENTITY);
    }

    @Test
    public void loginShouldReturnOptionalEmpty() {
        when(userDao.findByEmail(USER.getEmail())).thenReturn(Optional.empty());

        Optional<User> user = userService.login(USER.getEmail(), USER.getPassword());

        assertEquals(Optional.empty(), user);
        verify(userDao).findByEmail(USER.getEmail());
    }

    private static User initUser() {
        return User.builder()
                .withId(1L)
                .withEmail("liza@gmail.com")
                .withPassword("123")
                .build();
    }

    private static UserEntity initUserEntity() {
        return UserEntity.builder()
                .withId(1L)
                .withEmail("liza@gmail.com")
                .withPassword("123")
                .build();
    }
}