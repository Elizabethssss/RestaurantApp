package com.restaurant.entity;

import com.restaurant.domain.LunchType;

import java.time.LocalTime;
import java.util.List;

public class LunchEntity {
    private final Long id;
    private final String name;
    private final String description;
    private final String img;
    private final LunchType lunchType;
    private final List<DishEntity> dishEntities;

    public LunchEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.img = builder.img;
        this.lunchType = builder.lunchType;
        this.dishEntities = builder.dishEntities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getTimeFrom() {
        return lunchType.getTimeFrom();
    }

    public LocalTime getTimeTo() {
        return lunchType.getTimeTo();
    }

    public String getImg() {
        return img;
    }

    public LunchType getLunchType() {
        return lunchType;
    }

    public List<DishEntity> getDishEntities() {
        return dishEntities;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String img;
        private LunchType lunchType;
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

        public Builder withDescription(String description) {
            this.description = description;
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

        public Builder withLunchType(LunchType lunchType) {
            this.lunchType = lunchType;
            return this;
        }

        public LunchEntity build() {
            return new LunchEntity(this);
        }
    }
}
