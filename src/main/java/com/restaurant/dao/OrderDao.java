package com.restaurant.dao;

import com.restaurant.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

/**
 * Order DAO class which contains methods interaction with `orders` table in DB.
 */

public interface OrderDao extends PageableDao<OrderEntity> {
    List<OrderEntity> getOrdersByUserId(Long userId);

    Optional<OrderEntity> getOrderByStatusAndUserId(String status, Long userId);

    void addDishToOrder(Long orderId, Long dishId);

    void deleteOrderDishById(Long orderId, Long dishId, Integer quantity);

    void updateOrderStatus(Long orderId, String status);

    void updateOrderCostAndStatus(int totalPrice, Long orderId);

    List<OrderEntity> getOrdersByStatus(String status, Page page);

    int countByStatus(String status);

    void addLunchToOrder(Long orderId, long lunchId);

    void deleteOrderLunchById(Long orderId, Long lunchId, int quantity);
}
