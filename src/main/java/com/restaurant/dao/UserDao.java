package com.restaurant.dao;

import com.restaurant.entity.UserEntity;

import java.util.Optional;

/**
 * User DAO class which contains methods interaction with `users` table in DB.
 */

public interface UserDao extends GenericDao<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
