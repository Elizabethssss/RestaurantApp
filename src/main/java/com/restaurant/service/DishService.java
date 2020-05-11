package com.restaurant.service;

import com.restaurant.dao.Page;
import com.restaurant.domain.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DishService{
    List<Dish> getDishesByType(String dishType, Page page);
    int count(String dishType);
    Optional<Dish> getDishById(Long dishId);
    Map<Dish, Integer> getDishesByOrderId(Long orderId);
    List<Dish> getDishesByLunchId(Long lunchId);
}
