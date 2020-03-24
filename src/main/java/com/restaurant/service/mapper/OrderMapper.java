package com.restaurant.service.mapper;

import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;

public class OrderMapper implements Mapper<OrderEntity, Order> {
    @Override
    public Order mapEntityToDomain(OrderEntity entity) {
        return Order.builder()
                .withId(entity.getId())
                .withStatus(entity.getStatus())
                .withCreatedAt(entity.getCreatedAt())
                .withUser(entity.getUserEntity() == null?null:
                        User.builder().withId(entity.getUserEntity().getId()).build())
                .build();
    }

    @Override
    public OrderEntity mapDomainToEntity(Order item) {
        return OrderEntity.builder()
                .withId(item.getId())
                .withStatus(item.getStatus())
                .withCreatedAt(item.getCreatedAt())
                .withUserEntity(item.getUser() == null?null:
                        UserEntity.builder().withId(item.getUser().getId()).build())
                .build();
    }
}
