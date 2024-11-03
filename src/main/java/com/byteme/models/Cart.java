// Cart.java
package com.byteme.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String customerId;
    private Map<MenuItem, Integer> items;

    public Cart(String customerId) {
        this.customerId = customerId;
        this.items = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public void updateQuantity(MenuItem item, int quantity) {
        if (quantity <= 0) {
            items.remove(item);
        } else {
            items.put(item, quantity);
        }
    }

    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry .getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<MenuItem, Integer> getItems() {
        return new HashMap<>(items);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String getCustomerId() {
        return customerId;
    }
}