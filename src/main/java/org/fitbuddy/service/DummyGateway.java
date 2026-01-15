package org.fitbuddy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DummyGateway implements DataGateway {

    @Override
    public UserDTO getUserById(int userId) {
        return new UserDTO("Azmain", 20, 170, "Maintain");
    }

    @Override
    public Double getLatestWeight(int userId) {
        return 69.5;
    }

    @Override
    public Integer getCaloriesIntakeForDate(int userId, LocalDate date) {
        return 1800;
    }

    @Override
    public Integer getCaloriesBurnedForDate(int userId, LocalDate date) {
        return 450;
    }

    @Override
    public List<WeightDTO> getWeightLogsLastNDays(int userId, int days) {
        List<WeightDTO> list = new ArrayList<>();
        list.add(new WeightDTO(LocalDate.now().minusDays(4), 70.0));
        list.add(new WeightDTO(LocalDate.now().minusDays(3), 69.8));
        list.add(new WeightDTO(LocalDate.now().minusDays(2), 69.6));
        list.add(new WeightDTO(LocalDate.now().minusDays(1), 69.5));
        list.add(new WeightDTO(LocalDate.now(), 69.3));
        return list;
    }

    @Override
    public Double calculateBMI(double weightKg, double heightCm) {
        double h = heightCm / 100.0;
        return weightKg / (h * h);
    }
}
