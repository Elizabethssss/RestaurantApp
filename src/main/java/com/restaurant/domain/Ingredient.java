package com.restaurant.domain;

import java.util.List;

public class Ingredient {
    private final Long id;
    private final String name;
    private final String img;
    private final List<Dish> dishes;

    public Ingredient(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.img = builder.img;
        this.dishes = builder.dishes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Long id;
        private String name;
        private String img;
        private List<Dish> dishes;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withImg(String img) {
            this.img = img;
            return this;
        }

        public Builder withDishes(List<Dish> dishes) {
            this.dishes = dishes;
            return this;
        }

        public Ingredient build() {
            return new Ingredient(this);
        }
    }
}
