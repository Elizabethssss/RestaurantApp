package com.restaurant.service.mapper;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderMapper implements Mapper<OrderEntity, Order> {
    @Override
    public Order mapEntityToDomain(OrderEntity entity) {
        Map<Dish, Integer> dishIntegerMap = new LinkedHashMap<>();
        Map<Lunch, Integer> lunchIntegerMap = new LinkedHashMap<>();
        return Order.builder()
                .withId(entity.getId())
                .withStatus(entity.getStatus())
                .withCost(entity.getCost())
                .withCreatedAt(entity.getCreatedAt())
                .withUser(entity.getUserEntity() == null ? null : User.builder()
                        .withId(entity.getUserEntity().getId()).withEmail(entity.getUserEntity().getEmail()).build())
                .withDishes(dishIntegerMap)
                .withLunches(lunchIntegerMap)
                .build();
    }

    @Override
    public OrderEntity mapDomainToEntity(Order item) {
        return OrderEntity.builder()
                .withId(item.getId())
                .withStatus(item.getStatus())
                .withCost(item.getCost())
                .withCreatedAt(item.getCreatedAt())
                .withUserEntity(item.getUser() == null ? null : UserEntity.builder()
                        .withId(item.getUser().getId()).withEmail(item.getUser().getEmail()).build())
                .build();
    }
}
