package com.restaurant.entity;

import java.util.List;

public class IngredientEntity {
    private final Long id;
    private final String name;
    private final String img;
    private final List<DishEntity> dishEntities;

    public IngredientEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.img = builder.img;
        this.dishEntities = builder.dishEntities;
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

    public List<DishEntity> getDishEntities() {
        return dishEntities;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Long id;
        private String name;
        private String img;
        private List<DishEntity> dishEntities;

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

        public Builder withDishEntities(List<DishEntity> dishEntities) {
            this.dishEntities = dishEntities;
            return this;
        }

        public IngredientEntity build() {
            return new IngredientEntity(this);
        }
    }
}
