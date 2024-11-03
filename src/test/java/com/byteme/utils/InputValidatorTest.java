// InputValidatorTest.java
package com.byteme.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    @Test
    void testValidEmail() {
        assertTrue(InputValidator.isValidEmail("test@example.com"));
        assertFalse(InputValidator.isValidEmail("invalid-email"));
    }

    @Test
    void testValidPassword() {
        assertTrue(InputValidator.isValidPassword("StrongPass123!"));
        assertFalse(InputValidator.isValidPassword("weak"));
    }

    @Test
    void testValidMenuItemName() {
        assertTrue(InputValidator.isValidMenuItemName("Burger"));
        assertFalse(InputValidator.isValidMenuItemName(""));
    }

    @Test
    void testValidPrice() {
        assertTrue(InputValidator.isValidPrice(5.99));
        assertFalse(InputValidator.isValidPrice(-1.00));
    }

    @Test
    void testValidQuantity() {
        assertTrue(InputValidator.isValidQuantity(3));
        assertFalse(InputValidator.isValidQuantity(0));
    }
}