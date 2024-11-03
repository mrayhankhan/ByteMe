// OrderService.java
package com.byteme.services;

import com.byteme.models.Order;
import java.util.List;

public interface OrderService {
    void placeOrder(Order order);
    void updateOrderStatus(String orderId, String status);
    void cancelOrder(String orderId);
    Order getOrder(String orderId);
    List<Order> getPendingOrders();
    List<Order> getCustomerOrders(String customerId);
    void processRefund(String orderId);
    DailyReport generateDailyReport(Date date);
    List<Order> getOrdersByDateRange(Date startDate, Date endDate);
    DailyReport getDailyReport(String reportId);
}