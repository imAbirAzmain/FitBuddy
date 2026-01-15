package org.fitbuddy.model;

public class WeightPoint {
    private final String dateLabel;   // e.g., "Jan 14"
    private final double weight;

    public WeightPoint(String dateLabel, double weight) {
        this.dateLabel = dateLabel;
        this.weight = weight;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public double getWeight() {
        return weight;
    }
}
