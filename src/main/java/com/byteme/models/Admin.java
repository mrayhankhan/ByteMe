// Admin.java
package com.byteme.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Admin extends User {
    private String employeeId;
    private String role;
    private List<String> permissions;
    private static MenuService menuService = new MenuServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public Admin(String id, String name, String email, String password, String employeeId, String role) {
        super(id, name, email, password);
        this.employeeId = employeeId;
        this.role = role;
        this.permissions = new ArrayList<>();
    }

    // Getters and setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public List<String> getPermissions() { return permissions; }
    public void addPermission(String permission) { this.permissions.add(permission); }
    public void removePermission(String permission) { this.permissions.remove(permission); }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    @Override
    public String toString() {
        return String.format("Admin{id='%s', name='%s', email='%s', employeeId='%s', role='%s'}",
            id, name, email, employeeId, role);
    }

    // Admin-specific handlers
    public void handleAddMenuItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        
        String id = UUID.randomUUID().toString();
        MenuItem newItem = new MenuItem(id, name, price, category);
        
        try {
            menuService.addMenuItem(newItem);
            System.out.println("Menu item added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add menu item: " + e.getMessage());
        }
    }

    public void handleUpdateMenuItem() {
        System.out.print("Enter item ID to update: ");
        String itemId = scanner.nextLine();
        
        try {
            MenuItem item = menuService.getMenuItem(itemId);
            
            System.out.println("Current details: " + item);
            System.out.print("Enter new price (or -1 to keep current): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Is item available? (y/n): ");
            String available = scanner.nextLine();
            
            if (price != -1) {
                item.setPrice(price);
            }
            item.setAvailable(available.equalsIgnoreCase("y"));
            
            menuService.updateMenuItem(item);
            System.out.println("Menu item updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Item not found!");
        }
    }

    public void handleRemoveMenuItem() {
        System.out.print("Enter item ID to remove: ");
        String itemId = scanner.nextLine();
        
        try {
            menuService.removeMenuItem(itemId);
            System.out.println("Menu item removed successfully!");
        } catch (NotFoundException e) {
            System.out.println("Item not found!");
        }
    }

    public void handleViewPendingOrders() {
        List<Order> pendingOrders = orderService.getPendingOrders();
        
        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders!");
            return;
        }
        
        System.out.println("\n=== Pending Orders ===");
        pendingOrders.forEach(System.out::println);
    }

    public void handleUpdateOrderStatus() {
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        
        System.out.println("Select new status:");
        System.out.println("1. PREPARING");
        System.out.println("2. READY");
        System.out.println("3. OUT_FOR_DELIVERY");
        System.out.println("4. DELIVERED");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        String status;
        switch (choice) {
            case 1: status = "PREPARING"; break;
            case 2: status = "READY"; break;
            case 3: status = "OUT_FOR_DELIVERY"; break;
            case 4: status = "DELIVERED"; break;
            default: 
                System.out.println("Invalid choice!");
                return;
        }
        
        try {
            orderService.updateOrderStatus(orderId, status);
            System.out.println("Order status updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Order not found!");
        }
    }

    public void handleProcessRefund() {
        System.out.print("Enter order ID for refund: ");
        String orderId = scanner.nextLine();
        
        try {
            orderService.processRefund(orderId);
            System.out.println("Refund processed successfully!");
        } catch (NotFoundException e) {
            System.out.println("Order not found!");
        }
    }

    public void handleGenerateDailyReport() {
        DailyReport report = orderService.generateDailyReport(new Date());
        System.out.println(report);
    }

    public void handleSearchMenu() {
        System.out.println("\n=== Search Menu ===");
        System.out.println("1. Search by keyword");
        System.out.println("2. Filter by category");
        System.out.println("3. Sort by price");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        List<MenuItem> results = null;
        
        switch (choice) {
            case 1:
                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine();
                results = menuService.searchItems(keyword);
                break;
            case 2:
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                results = menuService.filterByCategory(category);
                break;
            case 3:
                System.out.print("Sort ascending? (y/n): ");
                String ascending = scanner.nextLine();
                results = menuService.sortByPrice(ascending.equalsIgnoreCase("y"));
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        if (results.isEmpty()) {
            System.out.println("No items found!");
        } else {
            System.out.println("\n=== Search Results ===");
            results.forEach(System.out::println);
        }
    }
}