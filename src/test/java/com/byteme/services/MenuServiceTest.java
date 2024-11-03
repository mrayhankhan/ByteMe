// MenuServiceTest.java
package com.byteme.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuServiceTest {
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        menuService = new MenuServiceImpl();
        // Add some test data
        menuService.addMenuItem(new MenuItem("1", "Burger", 5.99, "Fast Food"));
        menuService.addMenuItem(new MenuItem("2", "Pizza", 8.99, "Fast Food"));
        menuService.addMenuItem(new MenuItem("3", "Salad", 4.99, "Healthy"));
    }

    @Test
    void testSearchItems() {
        List<MenuItem> results = menuService.searchItems("burger");
        assertEquals(1, results.size());
        assertEquals("Burger", results.get(0).getName());
    }

    @Test
    void testFilterByCategory() {
        List<MenuItem> results = menuService.filterByCategory("Fast Food");
        assertEquals(2, results.size());
    }

    @Test
    void testSortByPrice() {
        List<MenuItem> results = menuService.sortByPrice(true);
        assertEquals("Salad", results.get(0).getName());
        assertEquals("Pizza", results.get(2).getName());
    }
}