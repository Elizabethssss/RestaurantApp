package com.restaurant.domain;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Lunch {
    private final Long id;
    private final String name;
    private final String description;
    private final String img;
    private final LunchType lunchType;
    private final List<Dish> dishes;

    public Lunch(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.img = builder.img;
        this.lunchType = builder.lunchType;
        this.dishes = builder.dishes;
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

    public int getPrice() {
        int price = 0;
        for (Dish dish : dishes) {
            price += dish.getPriceInt();
        }
        return (int) (price * 0.9);
    }

    public String getWeight() {
        String weight = "";
        for (Dish dish : dishes) {
            weight += dish.getWeight() + " ";
        }
        weight = weight.trim();
        weight = weight.replaceAll(" ", " / ");
        return weight;
    }

    public String getImg() {
        return img;
    }

    public LunchType getLunchType() {
        return lunchType;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private String img;
        private LunchType lunchType;
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

        public Builder withDescription(String description) {
            this.description = description;
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

        public Builder withLunchType(LunchType lunchType) {
            this.lunchType = lunchType;
            return this;
        }

        public Lunch build() {
            return new Lunch(this);
        }
    }
}
