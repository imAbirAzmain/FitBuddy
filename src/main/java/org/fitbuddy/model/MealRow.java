package org.fitbuddy.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MealRow {
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleIntegerProperty calories = new SimpleIntegerProperty();

    public MealRow(String date, String name, int calories) {
        this.date.set(date);
        this.name.set(name);
        this.calories.set(calories);
    }

    public String getDate() { return date.get(); }
    public String getName() { return name.get(); }
    public int getCalories() { return calories.get(); }
}
