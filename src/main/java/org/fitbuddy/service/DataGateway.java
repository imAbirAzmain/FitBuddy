package org.fitbuddy.service;

import java.time.LocalDate;
import java.util.List;

public interface DataGateway {

    // Profile
    UserDTO getUserById(int userId);

    // Dashboard
    Double getLatestWeight(int userId);
    Integer getCaloriesIntakeForDate(int userId, LocalDate date);
    Integer getCaloriesBurnedForDate(int userId, LocalDate date);

    // Charts
    List<WeightDTO> getWeightLogsLastNDays(int userId, int days);

    // Calculation (from Person 1 utility)
    Double calculateBMI(double weightKg, double heightCm);
}
