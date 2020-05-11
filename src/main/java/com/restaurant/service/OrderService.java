package com.restaurant.service;

import com.restaurant.dao.Page;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getOrdersByUserId(Long userId);
    Optional<Order> getOrderByStatusAndUserId(OrderStatus status, Long userId);
    void addOrder(Order order);
    void addDishToOrder(Long orderId, Long dishId);
    void deleteOrderDishById(Long orderId, Long dishId, Integer quantity);
    List<Order> getOrdersExceptFormed(Long userId, Page page);
    void updateOrderStatus(Long orderId, OrderStatus status);
    void updateOrderCostAndStatus(int totalPrice, Long orderId);
    int count(Long userId);
    Optional<Order> getOrderById(Long orderId);
    List<Order> getOrdersByStatus(OrderStatus status, Page page);
    int countOrdersByStatus(OrderStatus status);

    void addLunchToOrder(Long orderId, long lunchId);

    void deleteOrderLunchById(Long orderId, Long lunchId, int quantity);
}
