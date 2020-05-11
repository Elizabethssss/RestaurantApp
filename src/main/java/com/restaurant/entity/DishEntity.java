package com.restaurant.entity;

import com.restaurant.domain.DishType;

import java.util.List;

public class DishEntity {
    private final Long id;
    private final String name;
    private final String about;
    private final DishType dishType;
    private final double price;
    private final int weight;
    private final String img;
    private final List<OrderEntity> orderEntities;
    private final List<LunchEntity> lunchEntities;
    private final List<IngredientEntity> ingredientEntities;

    public DishEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.about = builder.about;
        this.dishType = builder.dishType;
        this.price = builder.price;
        this.weight = builder.weight;
        this.img = builder.img;
        this.orderEntities = builder.orderEntities;
        this.lunchEntities = builder.lunchEntities;
        this.ingredientEntities = builder.ingredientEntities;
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

    public int getWeight() {
        return weight;
    }

    public String getImg() {
        return img;
    }

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public List<LunchEntity> getLunchEntities() {
        return lunchEntities;
    }

    public List<IngredientEntity> getIngredientEntities() {
        return ingredientEntities;
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
        private List<OrderEntity> orderEntities;
        private List<LunchEntity> lunchEntities;
        private List<IngredientEntity> ingredientEntities;

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

        public Builder withOrderEntities(List<OrderEntity> orderEntities) {
            this.orderEntities = orderEntities;
            return this;
        }

        public Builder withLunchEntities(List<LunchEntity> lunchEntities) {
            this.lunchEntities = lunchEntities;
            return this;
        }

        public Builder withIngredientEntities(List<IngredientEntity> ingredientEntities) {
            this.ingredientEntities = ingredientEntities;
            return this;
        }

        public DishEntity build() {
            return new DishEntity(this);
        }
    }
}
