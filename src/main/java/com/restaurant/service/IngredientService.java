package com.restaurant.service;

import com.restaurant.domain.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findIngredientsByDishId(Long dishId);
}
