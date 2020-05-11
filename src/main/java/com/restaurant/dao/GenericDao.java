package com.restaurant.dao;

import java.util.Optional;

/**
 * Base DAO class which contains crud interactions with DB.
 * @param <T>
 */

public interface GenericDao<T> {
    boolean save(T object);
    Optional<T> findById(long id);
    boolean update(T object);
    boolean delete(long id);
}
