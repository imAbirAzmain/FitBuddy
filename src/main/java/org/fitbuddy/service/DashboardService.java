package org.fitbuddy.service;

import java.time.LocalDate;

public class DashboardService {

    private final DataGateway gateway = new DummyGateway();

    public double getLatestWeight(int userId) {
        Double w = gateway.getLatestWeight(userId);
        return w != null ? w : 0.0;
    }

    public double getBmi(int userId) {
        UserDTO user = gateway.getUserById(userId);
        Double w = gateway.getLatestWeight(userId);

        if (user == null || w == null) return 0.0;

        Double bmi = gateway.calculateBMI(w, user.heightCm);
        return bmi != null ? bmi : 0.0;
    }

    public int getCaloriesIntakeToday(int userId) {
        Integer v = gateway.getCaloriesIntakeForDate(userId, LocalDate.now());
        return v != null ? v : 0;
    }

    public int getCaloriesBurnedToday(int userId) {
        Integer v = gateway.getCaloriesBurnedForDate(userId, LocalDate.now());
        return v != null ? v : 0;
    }
}
