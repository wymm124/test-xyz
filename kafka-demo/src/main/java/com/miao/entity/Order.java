package com.miao.entity;

/**
 * 订单
 */
public class Order {

    private Integer orderId;

    private Integer count;

    public Order() {
    }

    public Order(Integer orderId, Integer count) {
        this.orderId = orderId;
        this.count = count;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
