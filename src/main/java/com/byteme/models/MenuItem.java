// MenuItem.java
package com.byteme.models;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String id;
    private String name;
    private double price;
    private String category;
    private boolean available;
    private List<Review> reviews;

    public MenuItem(String id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = true;
        this.reviews = new ArrayList<>();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public List<Review> getReviews() { return reviews; }
    public void addReview(Review review) { this.reviews.add(review); }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f) [%s] %s",
                id, name, price, category, available ? "Available" : "Not Available");
    }
}