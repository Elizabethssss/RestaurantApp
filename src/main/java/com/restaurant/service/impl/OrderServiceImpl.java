package com.restaurant.service.impl;

import com.restaurant.dao.OrderDao;
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
}
