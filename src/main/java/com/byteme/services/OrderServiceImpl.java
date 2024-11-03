// OrderServiceImpl.java
package com.byteme.services;

import com.byteme.models.Order;
import com.byteme.models.DailyReport;
import com.byteme.exceptions.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private Map<String, Order> orders = new HashMap<>();
    private PriorityQueue<Order> pendingOrders = new PriorityQueue<>();
    private Map<String, DailyReport> dailyReports = new HashMap<>();

    @Override
    public void placeOrder(Order order) {
        orders.put(order.getOrderId(), order);
        pendingOrders.offer(order);
    }

    @Override
    public DailyReport generateDailyReport(Date date) {
        String reportId = String.format("REPORT_%tF", date);
        DailyReport report = new DailyReport(reportId);
        
        // Get all orders for the specified date
        List<Order> dailyOrders = orders.values().stream()
            .filter(order -> isSameDay(order.getOrderTime(), date))
            .collect(Collectors.toList());
            
        // Process each order for the report
        for (Order order : dailyOrders) {
            report.addOrder(order);
        }
        
        dailyReports.put(reportId, report);
        return report;
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        Order order = getOrder(orderId);
        order.setStatus(status);
        if (!status.equals("PENDING")) {
            pendingOrders.remove(order);
        }
    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = getOrder(orderId);
        order.setStatus("CANCELLED");
        pendingOrders.remove(order);
    }

    @Override
    public Order getOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new NotFoundException("Order not found: " + orderId);
        }
        return order;
    }

    @Override
    public List<Order> getPendingOrders() {
        return new ArrayList<>(pendingOrders);
    }

    @Override
    public List<Order> getCustomerOrders(String customerId) {
        return orders.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .sorted(Comparator.comparing(Order::getOrderTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void processRefund(String orderId) {
        Order order = getOrder(orderId);
        // Implementation for processing refund would go here
        // This might involve integration with a payment system
        order.setStatus("REFUNDED");
    }

    @Override
    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
        return orders.values().stream()
            .filter(order -> order.getOrderTime().after(startDate) && 
                           order.getOrderTime().before(endDate))
            .collect(Collectors.toList());
    }

    @Override
    public DailyReport getDailyReport(String reportId) {
        DailyReport report = dailyReports.get(reportId);
        if (report == null) {
            throw new NotFoundException("Report not found: " + reportId);
        }
        return report;
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}