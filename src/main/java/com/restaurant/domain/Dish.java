package com.restaurant.domain;

import java.util.List;

public class Dish {
    private final Long id;
    private final String name;
    private final String about;
    private final DishType dishType;
    private final double price;
    private final int weight;
    private final String img;
    private final List<Order> orders;
    private final List<Lunch> lunches;
    private final List<Ingredient> ingredients;

    public Dish(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.about = builder.about;
        this.dishType = builder.dishType;
        this.price = builder.price;
        this.weight = builder.weight;
        this.img = builder.img;
        this.orders = builder.orders;
        this.lunches = builder.lunches;
        this.ingredients = builder.ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public DishType getDishType() {
        return dishType;
    }

    public double getPrice() {
        return price;
    }

    public int getPriceInt() {
        return (int) price;
    }

    public int getWeight() {
        return weight;
    }

    public String getImg() {
        return img;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Lunch> getLunches() {
        return lunches;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Long id;
        private String name;
        private String about;
        private DishType dishType;
        private double price;
        private int weight;
        private String img;
        private List<Order> orders;
        private List<Lunch> lunches;
        private List<Ingredient> ingredients;

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

        public Builder withAbout(String about) {
            this.about = about;
            return this;
        }

        public Builder withDishType(DishType dishType) {
            this.dishType = dishType;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder withWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder withImage(String img) {
            this.img = img;
            return this;
        }

        public Builder withOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Builder withLunches(List<Lunch> lunches) {
            this.lunches = lunches;
            return this;
        }

        public Builder withIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Dish build() {
            return new Dish(this);
        }
    }
}
