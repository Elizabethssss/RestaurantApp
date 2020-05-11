package com.restaurant.domain;

public enum OrderStatus {
    FORMED("Making an order"),
    SENT("Waiting for confirmation"),
    CONFIRMED("Waiting for payment"),
    PAYED("Done");

    private String statusDesc;

    OrderStatus(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
