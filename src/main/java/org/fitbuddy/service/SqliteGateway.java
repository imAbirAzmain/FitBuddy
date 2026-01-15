package org.fitbuddy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will connect Person 3 (UI/Charts) to Person 1 (SQLite + DAO + Calc Utils).
 *
 * For now it returns demo values so the app runs.
 * When Person 1 completes DAOs, ONLY replace the TODO parts with real DAO calls.
 */
public class SqliteGateway implements DataGateway {

    // TODO: When Person 1 gives these classes, import them and create objects here.
    // Example:
    // private final UserDAO userDAO = new UserDAO();
    // private final WeightDAO weightDAO = new WeightDAO();
    // private final MealDAO mealDAO = new MealDAO();
    // private final WorkoutDAO workoutDAO = new WorkoutDAO();
    // private final CalcUtil calc = new CalcUtil();

    @Override
    public UserDTO getUserById(int userId) {
        // TODO: return userDAO.getUserById(userId) mapped into UserDTO

        // Demo fallback:
        return new UserDTO("Azmain", 20, 170, "Maintain");
    }

    @Override
    public Double getLatestWeight(int userId) {
        // TODO: return weightDAO.getLatestWeight(userId)

        return 69.5;
    }

    @Override
    public Integer getCaloriesIntakeForDate(int userId, LocalDate date) {
        // TODO: return mealDAO.getTotalCaloriesForDate(userId, date)

        return 1800;
    }

    @Override
    public Integer getCaloriesBurnedForDate(int userId, LocalDate date) {
        // TODO: return workoutDAO.getTotalBurnedForDate(userId, date)

        return 450;
    }

    @Override
    public List<WeightDTO> getWeightLogsLastNDays(int userId, int days) {
        // TODO: return weightDAO.getWeightLogsLastNDays(userId, days) mapped into WeightDTO list

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
        // TODO: return Person 1 CalcUtil.calculateBMI(weightKg, heightCm)

        double h = heightCm / 100.0;
        return weightKg / (h * h);
    }
}
