// MenuService.java
package com.byteme.services;

import com.byteme.models.MenuItem;
import java.util.List;

public interface MenuService {
    void addMenuItem(MenuItem item);
    void updateMenuItem(MenuItem item);
    void removeMenuItem(String itemId);
    MenuItem getMenuItem(String itemId);
    List<MenuItem> getAllItems();
    List<MenuItem> searchItems(String keyword);
    List<MenuItem> filterByCategory(String category);
    List<MenuItem> sortByPrice(boolean ascending);
}