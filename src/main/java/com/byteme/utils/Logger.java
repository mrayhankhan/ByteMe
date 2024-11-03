// Logger.java
package com.byteme.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logInfo(String message) {
        System.out.println(formatMessage("INFO", message));
    }

    public static void logWarning(String message) {
        System.out.println(formatMessage("WARNING", message));
    }

    public static void logError(String message) {
        System.err.println(formatMessage("ERROR", message));
    }

    private static String formatMessage(String level, String message) {
        return String.format("[%s] [%s] %s", LocalDateTime.now().format(DATE_TIME_FORMATTER), level, message);
    }
}