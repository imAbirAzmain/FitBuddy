package org.fitbuddy.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WorkoutRow {
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleIntegerProperty minutes = new SimpleIntegerProperty();
    private final SimpleIntegerProperty caloriesBurned = new SimpleIntegerProperty();

    public WorkoutRow(String date, String name, int minutes, int caloriesBurned) {
        this.date.set(date);
        this.name.set(name);
        this.minutes.set(minutes);
        this.caloriesBurned.set(caloriesBurned);
    }

    public String getDate() { return date.get(); }
    public String getName() { return name.get(); }
    public int getMinutes() { return minutes.get(); }
    public int getCaloriesBurned() { return caloriesBurned.get(); }
}
