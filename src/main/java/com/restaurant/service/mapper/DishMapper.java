package com.restaurant.service.mapper;

import com.restaurant.domain.Dish;
import com.restaurant.entity.DishEntity;

public class DishMapper implements Mapper<DishEntity, Dish> {
    @Override
    public Dish mapEntityToDomain(DishEntity entity) {
        return Dish.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withAbout(entity.getAbout())
                .withDishType(entity.getDishType())
                .withPrice(entity.getPrice())
                .withWeight(entity.getWeight())
                .withImage(entity.getImg())
                .build();
    }

    @Override
    public DishEntity mapDomainToEntity(Dish item) {
        return DishEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withAbout(item.getAbout())
                .withDishType(item.getDishType())
                .withPrice(item.getPrice())
                .withWeight(item.getWeight())
                .withImage(item.getImg())
                .build();
    }
}
