package com.sergio.domain;

public class User {
    private int userId;
    private String name;
    private String paswsword;
    private Order order;

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
