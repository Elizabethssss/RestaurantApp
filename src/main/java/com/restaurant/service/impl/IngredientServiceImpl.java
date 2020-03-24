package com.restaurant.service.impl;

import com.restaurant.dao.IngredientDao;
import com.restaurant.domain.Ingredient;
import com.restaurant.entity.IngredientEntity;
import com.restaurant.service.IngredientService;
import com.restaurant.service.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientServiceImpl implements IngredientService {
    private final IngredientDao ingredientDao;
    private final Mapper<IngredientEntity, Ingredient> ingredientMapper;

    public IngredientServiceImpl(IngredientDao ingredientDao, Mapper<IngredientEntity, Ingredient> ingredientMapper) {
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public List<Ingredient> findIngredientsByDishId(Long dishId) {
        return ingredientDao.findIngredientsByDishId(dishId).stream()
                .map(ingredientMapper::mapEntityToDomain).collect(Collectors.toList());
    }
}
