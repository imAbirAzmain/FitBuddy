package org.fitbuddy.gateway;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAuthGateway implements AuthGateway {

    private static class UserRec {
        int id;
        String username;
        String password;
        UserRec(int id, String username, String password) {
            this.id = id; this.username = username; this.password = password;
        }
    }

    private final Map<String, UserRec> users = new HashMap<>();
    private int nextId = 2;

    public InMemoryAuthGateway() {
        users.put("admin", new UserRec(1, "admin", "1234")); // demo user
    }

    @Override
    public int login(String username, String password) {
        UserRec u = users.get(username.toLowerCase());
        if (u == null) return -1;
        return u.password.equals(password) ? u.id : -1;
    }

    @Override
    public boolean register(String username, String password) {
        String key = username.toLowerCase();
        if (users.containsKey(key)) return false;
        users.put(key, new UserRec(nextId++, username, password));
        return true;
    }

    @Override
    public boolean usernameExists(String username) {
        return users.containsKey(username.toLowerCase());
    }
}
