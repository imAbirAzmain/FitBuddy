package org.fitbuddy.util;

public class Validators {

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static Integer parseIntSafe(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return null; }
    }

    public static Double parseDoubleSafe(String s) {
        try { return Double.parseDouble(s.trim()); } catch (Exception e) { return null; }
    }
}
