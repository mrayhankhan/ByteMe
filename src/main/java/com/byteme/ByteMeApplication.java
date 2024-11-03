package com.byteme;

import com.byteme.models.*;
import com.byteme.services.*;
import java.util.*;

public class ByteMeApp {
    private static Scanner scanner = new Scanner(System.in);
    private static MenuService menuService = new MenuServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();
    private static UserService userService = new UserServiceImpl();
    private static Customer currentUser = null;

    public static void main(String[] args) {
        initializeTestData();

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else if (currentUser instanceof Admin) {
                showAdminMenu();
            } else {
                showCustomerMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n=== Welcome to Byte Me! ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleRegistration();
                break;
            case 3:
                System.out.println("Thank you for using Byte Me!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void showCustomerMenu() {
        System.out.println("\n=== Customer Menu ===");
        System.out.println("1. View Menu");
        System.out.println("2. Search Menu");
        System.out.println("3. View Cart");
        System.out.println("4. View Orders");
        System.out.println("5. Become VIP");
        System.out.println("6. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleViewMenu();
                break;
            case 2:
                handleSearchMenu();
                break;
            case 3:
                handleViewCart();
                break;
            case 4:
                handleViewOrders();
                break;
            case 5:
                handleVIPMembership();
                break;
            case 6:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void showAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View Menu");
        System.out.println("2. Add Menu Item");
        System.out.println("3. Update Menu Item");
        System.out.println("4. Remove Menu Item");
        System.out.println("5. View Pending Orders");
        System.out.println("6. Update Order Status");
        System.out.println("7. Process Refund");
        System.out.println("8. Generate Daily Report");
        System.out.println("9. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleViewMenu();
                break;
            case 2:
                handleAddMenuItem();
                break;
            case 3:
                handleUpdateMenuItem();
                break;
            case 4:
                handleRemoveMenuItem();
                break;
            case 5:
                handleViewPendingOrders();
                break;
            case 6:
                handleUpdateOrderStatus();
                break;
            case 7:
                handleProcessRefund();
                break;
            case 8:
                handleGenerateDailyReport();
                break;
            case 9:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void handleLogin() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            List<Customer> customers = userService.getAllCustomers();
            Optional<Customer> foundCustomer = customers.stream()
                    .filter(c -> c.getEmail().equals(email) && c.getPassword().equals(password))
                    .findFirst();

            if (foundCustomer.isPresent()) {
                currentUser = foundCustomer.get();
                System.out.println("Welcome back, " + currentUser.getName() + "!");
            } else {
                System.out.println("Invalid credentials!");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void handleRegistration() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String id = UUID.randomUUID().toString();
        Customer newCustomer = new Customer(id, name, email, password);

        try {
            userService.registerCustomer(newCustomer);
            System.out.println("Registration successful! Please login.");
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void handleViewMenu() {
        List<MenuItem> items = menuService.getAllItems();
        System.out.println("\n=== Menu ===");
        items.forEach(System.out::println);

        if (!(currentUser instanceof Admin)) {
            System.out.println("\n1. Add to cart");
            System.out.println("2. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                handleAddToCart();
            }
        }
    }

    private static void handleAddToCart() {
        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            MenuItem item = menuService.getMenuItem(itemId);
            if (!item.isAvailable()) {
                System.out.println("Sorry, this item is currently unavailable.");
                return;
            }

            Customer customer = (Customer) currentUser;
            customer.getCart().addItem(item, quantity);
            System.out.println("Item added to cart!");
        } catch (NotFoundException e) {
            System.out.println("Item not found!");
        }
    }

    private static void handleViewCart() {
        Customer customer = (Customer) currentUser;
        Cart cart = customer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        System.out.println("\n=== Your Cart ===");
        cart.getItems().forEach((item, quantity) ->
                System.out.printf("%s x%d - $%.2f\n", item.getName(), quantity, item.getPrice() * quantity));
        System.out.printf("Total: $%.2f\n", cart.getTotal());

        System.out.println("\n1. Checkout");
        System.out.println("2. Update quantity");
        System.out.println("3. Remove item");
        System.out.println("4. Back");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleCheckout();
                break;
            case 2:
                handleUpdateCartQuantity();
                break;
            case 3:
                handleRemoveFromCart();
                break;
        }
    }

    private static void handleCheckout() {
        Customer customer = (Customer) currentUser;
        Cart cart = customer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        System.out.print("Any special requests? (Enter for none): ");
        String specialRequests = scanner.nextLine();

        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, customer.getId(),
                new ArrayList<>(cart.getItems().keySet()),
                customer.isVIP(),
                specialRequests);

        try {
            orderService.placeOrder(order);
            cart.clear();
            System.out.println("Order placed successfully! Your order ID is: " + orderId);
        } catch (Exception e) {
            System.out.println("Failed to place order: " + e.getMessage());
        }
    }

    private static void handleViewOrders() {
        List<Order> orders = orderService.getCustomerOrders(currentUser.getId());

        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }

        System.out.println("\n=== Your Orders ===");
        orders.forEach(System.out::println);
    }

    private static void handleVIPMembership() {
        Customer customer = (Customer) currentUser;

        if (customer.isVIP()) {
            System.out.println("You are already a VIP member!");
            return;
        }

        System.out.printf("VIP membership costs $%.2f. Would you like to proceed? (y/n): ",
                VIPMembership.getVipMembershipFee());

        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            try {
                userService.addVIPMembership(customer.getId());
                System.out.println("Congratulations! You are now a VIP member!");
            } catch (Exception e) {
                System.out.println("Failed to process VIP membership: " + e.getMessage());
            }
        }
    }

    private static void initializeTestData() {
        // Add test menu items
        menuService.addMenuItem(new MenuItem("1", "Burger", 5.99, "Fast Food"));
        menuService.addMenuItem(new MenuItem("2", "Pizza", 8.99, "Fast Food"));
        menuService.addMenuItem(new MenuItem("3", "Salad", 4.99, "Healthy", false));
        menuService.addMenuItem(new MenuItem("4", "Pasta", 7.99, "Italian"));

        // Add test customers
        userService.registerCustomer(new Customer(UUID.randomUUID().toString(), "John Doe",
                "john@example.com", "password123"));
        userService.registerCustomer(new Admin(UUID.randomUUID().toString(), "Admin User",
                "admin@example.com", "adminpassword"));
    }

    // Handle admin-specific functionalities here
    private static void handleAddMenuItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter item category: ");
        String category = scanner.nextLine();

        String id = UUID.randomUUID().toString();
        MenuItem newItem = new MenuItem(id, name, price, category);

        menuService.addMenuItem(newItem);
        System.out.println("Item added successfully!");
    }

    private static void handleUpdateMenuItem() {
        System.out.print("Enter item ID to update: ");
        String itemId = scanner.nextLine();

        try {
            MenuItem item = menuService.getMenuItem(itemId);
            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.nextLine();
            System.out.print("Enter new price (leave blank to keep current): ");
            String priceInput = scanner.nextLine();
            System.out.print("Enter new category (leave blank to keep current): ");
            String category = scanner.nextLine();

            if (!name.isEmpty()) {
                item.setName(name);
            }
            if (!priceInput.isEmpty()) {
                double price = Double.parseDouble(priceInput);
                item.setPrice(price);
            }
            if (!category.isEmpty()) {
                item.setCategory(category);
            }

            menuService.updateMenuItem(item);
            System.out.println("Item updated successfully!");
        } catch (NotFoundException e) {
            System.out.println("Item not found!");
        }
    }

    private static void handleRemoveMenuItem() {
        System.out.print("Enter item ID to remove: ");
        String itemId = scanner.nextLine();

        try {
            menuService.removeMenuItem(itemId);
            System.out.println("Item removed successfully!");
        } catch (NotFoundException e) {
            System.out.println("Item not found!");
        }
    }

    private static void handleViewPendingOrders() {
        List<Order> pendingOrders = orderService.getPendingOrders();

        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        System.out.println("\n=== Pending Orders ===");
        pendingOrders.forEach(System.out::println);
    }

    private static void handleUpdateOrderStatus() {
        System.out.print("Enter order ID to update: ");
        String orderId = scanner.nextLine();
        System.out.println("1. Processing\n2. Delivered\n3. Canceled");
        int statusChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        OrderStatus status;
        switch (statusChoice) {
            case 1:
                status = OrderStatus.PROCESSING;
                break;
            case 2:
                status = OrderStatus.DELIVERED;
                break;
            case 3:
                status = OrderStatus.CANCELED;
                break;
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

    private static void handleProcessRefund() {
        System.out.print("Enter order ID to refund: ");
        String orderId = scanner.nextLine();

        try {
            orderService.processRefund(orderId);
            System.out.println("Refund processed successfully!");
        } catch (NotFoundException e) {
            System.out.println("Order not found!");
        }
    }

    private static void handleGenerateDailyReport() {
        double totalSales = orderService.calculateTotalSales();
        int totalOrders = orderService.getTotalOrders();

        System.out.println("\n=== Daily Report ===");
        System.out.printf("Total Sales: $%.2f\n", totalSales);
        System.out.printf("Total Orders: %d\n", totalOrders);
    }

    private static void handleSearchMenu() {
        System.out.print("Enter item name to search: ");
        String searchQuery = scanner.nextLine();
        List<MenuItem> searchResults = menuService.searchMenuItems(searchQuery);

        if (searchResults.isEmpty()) {
            System.out.println("No matching items found.");
        } else {
            System.out.println("\n=== Search Results ===");
            searchResults.forEach(System.out::println);
        }
    }

    private static void handleUpdateCartQuantity() {
        Customer customer = (Customer) currentUser;
        Cart cart = customer.getCart();

        System.out.print("Enter item ID to update quantity: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            MenuItem item = menuService.getMenuItem(itemId);
            if (newQuantity <= 0) {
                cart.removeItem(item);
                System.out.println("Item removed from cart.");
            } else {
                cart.updateQuantity(item, newQuantity);
                System.out.println("Cart updated successfully.");
            }
        } catch (NotFoundException e) {
            System.out.println("Item not found in cart!");
        }
    }

    private static void handleRemoveFromCart() {
        Customer customer = (Customer) currentUser;
        Cart cart = customer.getCart();

        System.out.print("Enter item ID to remove: ");
        String itemId = scanner.nextLine();

        try {
            MenuItem item = menuService.getMenuItem(itemId);
            cart.removeItem(item);
            System.out.println("Item removed from cart.");
        } catch (NotFoundException e) {
            System.out.println("Item not found in cart!");
        }
    }
}
