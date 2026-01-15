package org.fitbuddy.service;

import org.fitbuddy.gateway.DailyLogGateway;
import org.fitbuddy.model.*;

import java.util.List;

public class DailyLogService {
    private final DailyLogGateway gateway;

    public DailyLogService(DailyLogGateway gateway) {
        this.gateway = gateway;
    }

    public List<StepsRow> getSteps(int userId) { return gateway.getSteps(userId); }
    public List<MealRow> getMeals(int userId) { return gateway.getMeals(userId); }
    public List<WorkoutRow> getWorkouts(int userId) { return gateway.getWorkouts(userId); }
    public List<SleepRow> getSleeps(int userId) { return gateway.getSleeps(userId); }

    public void addSteps(int userId, StepsRow row) { gateway.addSteps(userId, row); }
    public void addMeal(int userId, MealRow row) { gateway.addMeal(userId, row); }
    public void addWorkout(int userId, WorkoutRow row) { gateway.addWorkout(userId, row); }
    public void addSleep(int userId, SleepRow row) { gateway.addSleep(userId, row); }

    public void deleteSteps(int userId, StepsRow row) { gateway.deleteSteps(userId, row); }
    public void deleteMeal(int userId, MealRow row) { gateway.deleteMeal(userId, row); }
    public void deleteWorkout(int userId, WorkoutRow row) { gateway.deleteWorkout(userId, row); }
    public void deleteSleep(int userId, SleepRow row) { gateway.deleteSleep(userId, row); }
}
