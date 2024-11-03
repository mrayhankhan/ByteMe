//OrderServiceTest.java
package com.byteme.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl();
    }

    @Test
    void testPlaceOrder() {
        Order order = new Order("1", "customer1", Arrays.asList(new MenuItem("1", "Burger", 5.99, "Fast Food")), true, "No special requests");
        orderService.placeOrder(order);
        assertEquals(1, orderService.getPendingOrders().size());
    }

    @Test
    void testUpdateOrderStatus() {
        Order order = new Order("1", "customer1", Arrays.asList(new MenuItem("1", "Burger", 5.99, "Fast Food")), true, "No special requests");
        orderService.placeOrder(order);
        orderService.updateOrderStatus("1", "IN_PROGRESS");
        assertEquals("IN_PROGRESS", orderService.getOrder("1").getStatus());
    }

    @Test
    void testGetPendingOrders() {
        Order order1 = new Order("1", "customer1", Arrays.asList(new MenuItem("1", "Burger", 5.99, "Fast Food")), true, "No special requests");
        Order order2 = new Order("2", "customer2", Arrays.asList(new MenuItem("2", "Pizza", 8.99, "Fast Food")), false, "No special requests");
        orderService.placeOrder(order1);
        orderService.placeOrder(order2);
        List<Order> pendingOrders = orderService.getPendingOrders();
        assertEquals(2, pendingOrders.size());
    }
}