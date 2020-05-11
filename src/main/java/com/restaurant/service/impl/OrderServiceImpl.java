package com.restaurant.service.impl;

import com.restaurant.dao.OrderDao;
import com.restaurant.dao.Page;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.entity.OrderEntity;
import com.restaurant.service.OrderService;
import com.restaurant.service.mapper.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final Mapper<OrderEntity, Order> orderMapper;

    public OrderServiceImpl(OrderDao orderDao, Mapper<OrderEntity, Order> orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderDao.getOrdersByUserId(userId).stream()
                .map(orderMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Order> getOrderByStatusAndUserId(OrderStatus status, Long userId) {
        return orderDao.getOrderByStatusAndUserId(status.name(), userId).map(orderMapper::mapEntityToDomain);
    }

    @Override
    public void addOrder(Order order) {
        orderDao.save(orderMapper.mapDomainToEntity(order));
    }

    @Override
    public void addDishToOrder(Long orderId, Long dishId) {
        orderDao.addDishToOrder(orderId, dishId);
    }

    @Override
    public void deleteOrderDishById(Long orderId, Long dishId, Integer quantity) {
        orderDao.deleteOrderDishById(orderId, dishId, quantity);
    }

    @Override
    public List<Order> getOrdersExceptFormed(Long userId, Page page) {
        return orderDao.findAll(userId, page).stream()
                .map(orderMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        orderDao.updateOrderStatus(orderId, status.name());
    }

    @Override
    public void updateOrderCostAndStatus(int totalPrice, Long orderId) {
        orderDao.updateOrderCostAndStatus(totalPrice, orderId);
    }

    @Override
    public int count(Long userId) {
        return orderDao.count(userId);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderDao.findById(orderId).map(orderMapper::mapEntityToDomain);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status, Page page) {
        return orderDao.getOrdersByStatus(status.name(), page).stream()
                .map(orderMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public int countOrdersByStatus(OrderStatus status) {
        return orderDao.countByStatus(status.name());
    }

    @Override
    public void addLunchToOrder(Long orderId, long lunchId) {
        orderDao.addLunchToOrder(orderId, lunchId);
    }

    @Override
    public void deleteOrderLunchById(Long orderId, Long lunchId, int quantity) {
        orderDao.deleteOrderLunchById(orderId, lunchId, quantity);
    }

}
