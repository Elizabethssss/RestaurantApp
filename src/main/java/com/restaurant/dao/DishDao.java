package com.restaurant.dao;

import com.restaurant.domain.Dish;
import com.restaurant.entity.DishEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DishDao extends PageableDao<DishEntity> {
    Map<Long, DishEntity> getDishesByOrderId(Long orderId);
}
