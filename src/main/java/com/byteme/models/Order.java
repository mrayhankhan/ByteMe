// Order.java
package com.byteme.models;

import java.util.Date;
import java.util.List;

public class Order implements Comparable<Order> {
    private String orderId;
    private String customerId;
    private List<MenuItem> items;
    private String status;
    private Date orderTime;
    private boolean isVIPOrder;
    private String specialRequests;
    private double totalAmount;

    public Order(String orderId, String customerId, List<MenuItem> items, boolean isVIPOrder, String specialRequests) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.status = "PENDING";
        this.orderTime = new Date();
        this.isVIPOrder = isVIPOrder;
        this.specialRequests = specialRequests;
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    // Getters and setters
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public List<MenuItem> getItems() { return items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getOrderTime() { return orderTime; }
    public boolean isVIPOrder() { return isVIPOrder; }
    public String getSpecialRequests() { return specialRequests; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public int compareTo(Order other) {
        if (this.isVIPOrder && !other.isVIPOrder) return -1;
        if (!this.isVIPOrder && other.isVIPOrder) return 1;
        return this.orderTime.compareTo(other.orderTime);
    }

    @Override
    public String toString() {
        return String.format("Order %s - Customer: %s, Status: %s, Total: %.2f, VIP: %s",
                orderId, customerId, status, totalAmount, isVIPOrder);
    }
}