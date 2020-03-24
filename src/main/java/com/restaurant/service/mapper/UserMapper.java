package com.restaurant.service.mapper;

import com.restaurant.domain.User;
import com.restaurant.entity.UserEntity;
import com.restaurant.service.util.PasswordEncryptor;

import java.util.Optional;

public class UserMapper implements Mapper<UserEntity, User> {
    private final PasswordEncryptor passwordEncryptor;

    public UserMapper(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public User mapEntityToDomain(UserEntity entity) {
        return User.builder()
                .withId(entity.getId())
                .withUsername(entity.getUsername())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        return UserEntity.builder()
                .withId(Optional.ofNullable(user.getId()).orElse(0L))
                .withUsername(user.getUsername())
                .withEmail(user.getEmail())
                .withPassword(passwordEncryptor.encrypt(user.getPassword()))
                .withRole(user.getRole())
                .build();
    }
}
