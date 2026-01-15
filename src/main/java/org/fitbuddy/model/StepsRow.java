package org.fitbuddy.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StepsRow {
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleIntegerProperty steps = new SimpleIntegerProperty();

    public StepsRow(String date, int steps) {
        this.date.set(date);
        this.steps.set(steps);
    }

    public String getDate() { return date.get(); }
    public int getSteps() { return steps.get(); }
}
