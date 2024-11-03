// InputValidator.java
package com.byteme.utils;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$"
    );

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidMenuItemName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidPrice(double price) {
        return price >= 0;
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }
}