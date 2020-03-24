package com.restaurant.entity;

import com.restaurant.domain.Role;

import java.util.List;

public class UserEntity {
    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final Role role;
    private final List<OrderEntity> orderEntities;

    public UserEntity(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.orderEntities = builder.orderEntities;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<OrderEntity> getOrders() {
        return orderEntities;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private Role role;
        private List<OrderEntity> orderEntities;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withOrders(List<OrderEntity> orderEntities) {
            this.orderEntities = orderEntities;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
