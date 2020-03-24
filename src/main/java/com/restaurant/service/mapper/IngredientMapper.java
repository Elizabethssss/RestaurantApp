package com.restaurant.service.mapper;

import com.restaurant.domain.Ingredient;
import com.restaurant.entity.IngredientEntity;

public class IngredientMapper implements Mapper<IngredientEntity, Ingredient> {
    @Override
    public Ingredient mapEntityToDomain(IngredientEntity entity) {
        return Ingredient.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withImg(entity.getImg())
                .build();
    }

    @Override
    public IngredientEntity mapDomainToEntity(Ingredient item) {
        return IngredientEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withImg(item.getImg())
                .build();
    }
}
