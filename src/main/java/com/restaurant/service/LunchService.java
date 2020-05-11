package com.restaurant.service;

import com.restaurant.dao.Page;
import com.restaurant.domain.Lunch;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LunchService {
    List<Lunch> getLunchesByType(String dishType, Page page);
    int count(String dishType);
    Optional<Lunch> getLunchById(Long lunchId);
    Map<Lunch, Integer> getLunchesByOrderId(Long orderId);
}
