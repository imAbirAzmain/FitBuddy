package org.fitbuddy.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SleepRow {
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleDoubleProperty hours = new SimpleDoubleProperty();

    public SleepRow(String date, double hours) {
        this.date.set(date);
        this.hours.set(hours);
    }

    public String getDate() { return date.get(); }
    public double getHours() { return hours.get(); }
}
