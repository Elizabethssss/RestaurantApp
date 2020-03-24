package com.restaurant.dao;

import java.util.List;

public interface PageableDao<T> extends GenericDao<T> {
    List<T> findAll(Object obj, Page page);
    int count(Object obj);
}
