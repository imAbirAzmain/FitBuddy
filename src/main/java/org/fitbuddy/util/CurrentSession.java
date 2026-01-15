package org.fitbuddy.util;

public class CurrentSession {
    private static int userId = 1; // TEMP default (for testing)

    private CurrentSession() {}

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int id) {
        userId = id;
    }

    public static void clear() { userId=-1; }
}
