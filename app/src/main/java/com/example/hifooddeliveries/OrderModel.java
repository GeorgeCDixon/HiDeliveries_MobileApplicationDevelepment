package com.example.hifooddeliveries;

public class OrderModel {
    String orderId, customerId,
            itemId,
            riderId,
            total, confirmed;

    public OrderModel(String orderId, String customerId, String itemId, String riderId, String total, String cconfirmed) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.itemId = itemId;
        this.riderId = riderId;
        this.total = total;
        this.confirmed = cconfirmed;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}
