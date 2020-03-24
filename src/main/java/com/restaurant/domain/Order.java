package com.restaurant.domain;

import com.restaurant.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private final Long id;
    private final OrderStatus status;
    private final LocalDate createdAt;
    private final User user;
    private final List<Dish> dishes;

    public Order(Order.Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.user = builder.user;
        this.dishes = builder.dishes;
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

    public User getUser() {
        return user;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public static Order.Builder builder() { return new Order.Builder(); }

    public static class Builder {
        private Long id;
        private OrderStatus status;
        private LocalDate createdAt;
        private User user;
        private List<Dish> dishes;

        private Builder() {
        }

        public Order.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Order.Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order.Builder withCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Order.Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Order.Builder witDishes(List<Dish> dishes) {
            this.dishes = dishes;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
