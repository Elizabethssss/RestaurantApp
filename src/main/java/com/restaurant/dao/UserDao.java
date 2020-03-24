package com.restaurant.dao;

import com.restaurant.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
