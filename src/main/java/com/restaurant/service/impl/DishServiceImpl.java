package com.restaurant.service.impl;

import com.restaurant.dao.DishDao;
import com.restaurant.dao.Page;
import com.restaurant.domain.Dish;
import com.restaurant.entity.DishEntity;
import com.restaurant.service.DishService;
import com.restaurant.service.mapper.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DishServiceImpl implements DishService {
    private final DishDao dishDao;
    private final Mapper<DishEntity, Dish> dishMapper;

    public DishServiceImpl(DishDao dishDao, Mapper<DishEntity, Dish> dishMapper) {
        this.dishDao = dishDao;
        this.dishMapper = dishMapper;
    }

    @Override
    public Optional<Dish> getDishById(Long dishId) {
        return dishDao.findById(dishId).map(dishMapper::mapEntityToDomain);
    }

    @Override
    public Map<Long, Dish> getDishesByOrderId(Long orderId) {
        Map<Long, DishEntity> entityMap = dishDao.getDishesByOrderId(orderId);
        Map<Long, Dish> dishMap = new HashMap<>();
        for (Map.Entry<Long, DishEntity> entry : entityMap.entrySet()) {
            dishMap.put(entry.getKey(), dishMapper.mapEntityToDomain(entry.getValue()));
        }
        return dishMap;
    }

    @Override
    public List<Dish> getDishesByType(String dishType, Page page) {
        return dishDao.findAll(dishType, page).stream()
                .map(dishMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public int count(String dishType) {
        return dishDao.count(dishType);
    }
}
