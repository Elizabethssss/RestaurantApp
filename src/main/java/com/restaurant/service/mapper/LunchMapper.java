package com.restaurant.service.mapper;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.LunchEntity;

import java.util.stream.Collectors;

public class LunchMapper implements Mapper<LunchEntity, Lunch> {
    private final Mapper<DishEntity, Dish> dishMapper;

    public LunchMapper(Mapper<DishEntity, Dish> dishMapper) {
        this.dishMapper = dishMapper;
    }

    @Override
    public Lunch mapEntityToDomain(LunchEntity entity) {
        return Lunch.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withImg(entity.getImg())
                .withLunchType(entity.getLunchType())
                .withDishes(entity.getDishEntities().stream()
                        .map(dishMapper::mapEntityToDomain).collect(Collectors.toList()))
                .build();
    }

    @Override
    public LunchEntity mapDomainToEntity(Lunch item) {
        return LunchEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .withImg(item.getImg())
                .withLunchType(item.getLunchType())
                .withDishEntities(item.getDishes().stream()
                        .map(dishMapper::mapDomainToEntity).collect(Collectors.toList()))
                .build();
    }
}
