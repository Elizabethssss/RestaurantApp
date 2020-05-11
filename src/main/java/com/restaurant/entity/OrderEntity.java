package com.restaurant.entity;

import com.restaurant.domain.OrderStatus;

import java.time.LocalDate;

public class OrderEntity {
    private final Long id;
    private final OrderStatus status;
    private final int cost;
    private final LocalDate createdAt;
    private final UserEntity userEntity;

    public OrderEntity(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.cost = builder.cost;
        this.createdAt = builder.createdAt;
        this.userEntity = builder.userEntity;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getCost() {
        return cost;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private OrderStatus status;
        private int cost;
        private LocalDate createdAt;
        private UserEntity userEntity;

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

        public Builder withCost(int cost) {
            this.cost = cost;
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

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
