package org.fitbuddy.gateway;

import org.fitbuddy.model.*;

import java.util.List;

public interface DailyLogGateway {
    List<StepsRow> getSteps(int userId);
    List<MealRow> getMeals(int userId);
    List<WorkoutRow> getWorkouts(int userId);
    List<SleepRow> getSleeps(int userId);

    void addSteps(int userId, StepsRow row);
    void addMeal(int userId, MealRow row);
    void addWorkout(int userId, WorkoutRow row);
    void addSleep(int userId, SleepRow row);

    void deleteSteps(int userId, StepsRow row);
    void deleteMeal(int userId, MealRow row);
    void deleteWorkout(int userId, WorkoutRow row);
    void deleteSleep(int userId, SleepRow row);
}
