package org.fitbuddy.gateway;

import org.fitbuddy.model.*;

import java.util.*;

public class InMemoryDailyLogGateway implements DailyLogGateway {

    private static class Store {
        List<StepsRow> steps = new ArrayList<>();
        List<MealRow> meals = new ArrayList<>();
        List<WorkoutRow> workouts = new ArrayList<>();
        List<SleepRow> sleeps = new ArrayList<>();
    }

    private final Map<Integer, Store> db = new HashMap<>();

    public InMemoryDailyLogGateway() {
        Store s = getStore(1); // demo seed for userId=1
        s.steps.add(new StepsRow("Jan 14", 8420));
        s.steps.add(new StepsRow("Jan 15", 9130));

        s.meals.add(new MealRow("Jan 15", "Chicken Rice", 550));
        s.meals.add(new MealRow("Jan 15", "Protein Shake", 220));

        s.workouts.add(new WorkoutRow("Jan 15", "Jogging", 30, 250));
        s.workouts.add(new WorkoutRow("Jan 15", "Pushups", 15, 100));

        s.sleeps.add(new SleepRow("Jan 15", 7.2));
    }

    private Store getStore(int userId) {
        return db.computeIfAbsent(userId, k -> new Store());
    }

    @Override public List<StepsRow> getSteps(int userId) { return new ArrayList<>(getStore(userId).steps); }
    @Override public List<MealRow> getMeals(int userId) { return new ArrayList<>(getStore(userId).meals); }
    @Override public List<WorkoutRow> getWorkouts(int userId) { return new ArrayList<>(getStore(userId).workouts); }
    @Override public List<SleepRow> getSleeps(int userId) { return new ArrayList<>(getStore(userId).sleeps); }

    @Override public void addSteps(int userId, StepsRow row) { getStore(userId).steps.add(row); }
    @Override public void addMeal(int userId, MealRow row) { getStore(userId).meals.add(row); }
    @Override public void addWorkout(int userId, WorkoutRow row) { getStore(userId).workouts.add(row); }
    @Override public void addSleep(int userId, SleepRow row) { getStore(userId).sleeps.add(row); }

    @Override public void deleteSteps(int userId, StepsRow row) { getStore(userId).steps.remove(row); }
    @Override public void deleteMeal(int userId, MealRow row) { getStore(userId).meals.remove(row); }
    @Override public void deleteWorkout(int userId, WorkoutRow row) { getStore(userId).workouts.remove(row); }
    @Override public void deleteSleep(int userId, SleepRow row) { getStore(userId).sleeps.remove(row); }
}
