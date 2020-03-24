package com.restaurant.service;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getOrdersByUserId(Long userId);
    Optional<Order> getOrderByStatus(OrderStatus status);
    void addOrder(Order order);
    void addDishToOrder(Long orderId, Long dishId);

    void deleteOrderDishById(Long orderDishId);

//    List<Order> createNewOrderList();
}
