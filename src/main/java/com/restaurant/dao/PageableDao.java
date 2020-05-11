package com.restaurant.dao;

import java.util.List;

/**
 * Pageable DAO class which contains page operations with DB.
 * @param <T>
 */

public interface PageableDao<T> extends GenericDao<T> {
    List<T> findAll(Object obj, Page page);
    int count(Object obj);
}
