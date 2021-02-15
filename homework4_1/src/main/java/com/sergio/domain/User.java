package com.sergio.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static AtomicInteger count = new AtomicInteger(0);

    private int userId;
    private String name;
    private String paswsword;
    private Order order;

    public User(String name) {
        this.userId = count.incrementAndGet();
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaswsword() {
        return paswsword;
    }

    public void setPaswsword(String paswsword) {
        this.paswsword = paswsword;
    }
}
