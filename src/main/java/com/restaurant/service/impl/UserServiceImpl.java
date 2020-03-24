package com.restaurant.service.impl;

import com.restaurant.dao.UserDao;
import com.restaurant.domain.User;
import com.restaurant.entity.UserEntity;
import com.restaurant.injector.ApplicationInjector;
import com.restaurant.service.UserService;
import com.restaurant.service.mapper.Mapper;
import com.restaurant.service.validator.UserValidator;

import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final Mapper<UserEntity, User> userMapper;

    public UserServiceImpl(UserDao userDao, Mapper<UserEntity, User> userMapper) {
        this.userDao = userDao;
        this.userValidator = ApplicationInjector.getUserValidator();
        this.userMapper = userMapper;
    }

    @Override
    public Map<String, String> register(User user) {
        Map<String, String> errorsMessages = userValidator.validate(user);
        if(userDao.findByEmail(user.getEmail()).isPresent()) {
            errorsMessages.put("emailError", "Email is already used!");
        }
        if (errorsMessages.isEmpty()) {
            userDao.save(userMapper.mapDomainToEntity(user));
        }
        return errorsMessages;
    }

    @Override
    public Optional<User> login(String email, String password) {
        Optional<UserEntity> temp = userDao.findByEmail(email);
        if(temp.isPresent() && temp.get().getPassword().equals(password)) {
            return temp.map(userMapper::mapEntityToDomain);
        }
        return Optional.empty();
    }
}
