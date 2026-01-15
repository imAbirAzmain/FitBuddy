package org.fitbuddy.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class WeightRow {
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleDoubleProperty weight = new SimpleDoubleProperty();

    public WeightRow(String date, double weight) {
        this.date.set(date);
        this.weight.set(weight);
    }

    public String getDate() {
        return date.get();
    }

    public double getWeight() {
        return weight.get();
    }
}
