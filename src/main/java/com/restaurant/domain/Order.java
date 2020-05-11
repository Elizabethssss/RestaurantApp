package com.restaurant.domain;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private final Long id;
    private final OrderStatus status;
    private final int cost;
    private final LocalDate createdAt;
    private final User user;
    private final Map<Dish, Integer> dishes;
    private final Map<Lunch, Integer> lunches;

    public Order(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.cost = builder.cost;
        this.createdAt = builder.createdAt;
        this.user = builder.user;
        this.dishes = builder.dishes;
        this.lunches = builder.lunches;
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

    public User getUser() {
        return user;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public Map<Lunch, Integer> getLunches() {
        return lunches;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private OrderStatus status;
        private int cost;
        private LocalDate createdAt;
        private User user;
        private Map<Dish, Integer> dishes;
        private Map<Lunch, Integer> lunches;

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

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withDishes(Map<Dish, Integer> dishes) {
            this.dishes = dishes;
            return this;
        }

        public Builder withLunches(Map<Lunch, Integer> lunches) {
            this.lunches = lunches;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
