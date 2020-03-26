package com.restaurant.dao;

import com.restaurant.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDao<OrderEntity> {
    List<OrderEntity> getOrdersByUserId(Long userId);
    Optional<OrderEntity> getOrderByStatusAndUserId(String status, Long userId);
    void addDishToOrder(Long orderId, Long dishId);
    void deleteOrderDishById(Long orderId, Long dishId, Integer quantity);
}
