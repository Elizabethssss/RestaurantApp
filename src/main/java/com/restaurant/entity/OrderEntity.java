package com.restaurant.entity;

import com.restaurant.domain.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderEntity {
    private final Long id;
    private final OrderStatus status;
    private final LocalDate createdAt;
    private final UserEntity userEntity;
    private final List<DishEntity> dishEntities;

    public OrderEntity(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.userEntity = builder.userEntity;
        this.dishEntities = builder.dishEntities;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public List<DishEntity> getDishEntities() {
        return dishEntities;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private OrderStatus status;
        private LocalDate createdAt;
        private UserEntity userEntity;
        private List<DishEntity> dishEntities;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUserEntity(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public Builder withDishEntities(List<DishEntity> dishEntities) {
            this.dishEntities = dishEntities;
            return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
