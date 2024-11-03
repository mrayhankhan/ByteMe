// CartService.java
package com.byteme.services;

import com.byteme.models.MenuItem;
import java.util.Map;

public interface CartService {
    void addItem(MenuItem item, int quantity);
    void removeItem(MenuItem item);
    void updateQuantity(MenuItem item, int quantity);
    double getTotal();
    Map<MenuItem, Integer> getItems();
    void clear();
    boolean isEmpty();
}