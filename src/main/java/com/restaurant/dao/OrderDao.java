package com.restaurant.dao;

import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDao<OrderEntity> {
    List<OrderEntity> getOrdersByUserId(Long userId);
    Optional<OrderEntity> getOrderByStatus(String status);
    void addDishToOrder(Long orderId, Long dishId);
    void deleteOrderDishById(Long orderDishId);
}
