package com.restaurant.dao;

import com.restaurant.entity.IngredientEntity;

import java.util.List;

/**
 * Ingredient DAO class which contains methods interaction with `ingredients` table in DB.
 */

public interface IngredientDao extends GenericDao<IngredientEntity> {
    List<IngredientEntity> findIngredientsByDishId(Long dishId);
}
