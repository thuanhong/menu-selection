package com.project.coffee.Model;

public class Order {
    private String orderId;
    private Integer total;
    private String date;

    public Order() {
    }

    public Order(String orderId, Integer total, String date) {
        this.orderId = orderId;
        this.total = total;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
