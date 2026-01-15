package org.fitbuddy.gateway;

public interface AuthGateway {
    int login(String username, String password);     // returns userId, -1 if fail
    boolean register(String username, String password);
    boolean usernameExists(String username);
}
