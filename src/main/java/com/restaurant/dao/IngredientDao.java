package com.restaurant.dao;

import com.restaurant.entity.IngredientEntity;

import java.util.List;

public interface IngredientDao {
    List<IngredientEntity> findIngredientsByDishId(Long dishId);
}
