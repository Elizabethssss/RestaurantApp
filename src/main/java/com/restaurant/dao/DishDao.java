package com.restaurant.dao;

import com.restaurant.entity.DishEntity;

import java.util.List;
import java.util.Map;

/**
 * Dish DAO class which contains methods interaction with `dishes` table in DB.
 */

public interface DishDao extends PageableDao<DishEntity> {
    Map<DishEntity, Integer> getDishesByOrderId(Long orderId);
    List<DishEntity> getDishesByLunchId(Long lunchId);
}
