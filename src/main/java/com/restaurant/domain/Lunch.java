package com.restaurant.domain;

import java.time.LocalDate;
import java.util.List;

public class Lunch {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDate timeFrom;
    private final LocalDate timeTo;
    private final String img;
    private final LunchType lunchType;
    private final List<Dish> dishes;

    public Lunch(Lunch.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.timeFrom = builder.timeFrom;
        this.timeTo = builder.timeTo;
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

    public LocalDate getTimeFrom() {
        return timeFrom;
    }

    public LocalDate getTimeTo() {
        return timeTo;
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

    public static Lunch.Builder builder() { return new Lunch.Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private LocalDate timeFrom;
        private LocalDate timeTo;
        private String img;
        private LunchType lunchType;
        private List<Dish> dishes;

        private Builder() {
        }

        public Lunch.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Lunch.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Lunch.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Lunch.Builder withTimeFrom(LocalDate timeFrom) {
            this.timeFrom = timeFrom;
            return this;
        }

        public Lunch.Builder withTimeTo(LocalDate timeTo) {
            this.timeTo = timeTo;
            return this;
        }

        public Lunch.Builder withImg(String img) {
            this.img = img;
            return this;
        }

        public Lunch.Builder withDishes(List<Dish> dishes) {
            this.dishes = dishes;
            return this;
        }

        public Lunch.Builder withLunchType(LunchType lunchType) {
            this.lunchType = lunchType;
            return this;
        }

        public Lunch build() {
            return new Lunch(this);
        }
    }
}
