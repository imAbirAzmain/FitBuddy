package org.fitbuddy.service;

import org.fitbuddy.gateway.AuthGateway;

public class AuthService {
    private final AuthGateway gateway;

    public AuthService(AuthGateway gateway) {
        this.gateway = gateway;
    }

    public int login(String username, String password) {
        return gateway.login(username, password);
    }

    public boolean register(String username, String password) {
        return gateway.register(username, password);
    }

    public boolean usernameExists(String username) {
        return gateway.usernameExists(username);
    }
}
