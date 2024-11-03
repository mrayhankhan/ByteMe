// MenuServiceImpl.java
package com.byteme.services;

import com.byteme.models.MenuItem;
import com.byteme.exceptions.NotFoundException;
import java.util.*;

public class MenuServiceImpl implements MenuService {
    private Map<String, MenuItem> menuItems = new HashMap<>();

    @Override
    public void addMenuItem(MenuItem item) {
        menuItems.put(item.getId(), item);
    }

    @Override
    public void updateMenuItem(MenuItem item) {
        if (!menuItems.containsKey(item.getId())) {
            throw new NotFoundException("Menu item not found: " + item.getId());
        }
        menuItems.put(item.getId(), item);
    }

    @Override
    public void removeMenuItem(String itemId) {
        if (!menuItems.containsKey(itemId)) {
            throw new NotFoundException("Menu item not found: " + itemId);
        }
        menuItems.remove(itemId);
    }

    @Override
    public MenuItem getMenuItem(String itemId) {
        MenuItem item = menuItems.get(itemId);
        if (item == null) {
            throw new NotFoundException("Menu item not found: " + itemId);
        }
        return item;
    }

    @Override
    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems.values());
    }

    @Override
    public List<MenuItem> searchItems(String keyword) {
        return menuItems.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> filterByCategory(String category) {
        return menuItems.values().stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> sortByPrice(boolean ascending) {
        return menuItems.values().stream()
                .sorted(ascending ?
                        Comparator.comparing(MenuItem::getPrice) :
                        Comparator.comparing(MenuItem::getPrice).reversed())
                .collect(Collectors.toList());
    }
}