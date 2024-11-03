// CartServiceImpl.java
package com.byteme.services;

import com.byteme.models.Cart;
import com.byteme.models.MenuItem;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private Cart cart;

    public CartServiceImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addItem(MenuItem item, int quantity) {
        cart.addItem(item, quantity);
    }

    @Override
    public void removeItem(MenuItem item) {
        cart.removeItem(item);
    }

    @Override
    public void updateQuantity(MenuItem item, int quantity) {
        cart.updateQuantity(item, quantity);
    }

    @Override
    public double getTotal() {
        return cart.getTotal();
    }

    @Override
    public Map<MenuItem, Integer> getItems() {
        return cart.getItems();
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public boolean isEmpty() {
        return cart.isEmpty();
    }
}