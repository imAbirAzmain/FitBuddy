package org.fitbuddy.service;

import java.time.LocalDate;

public class WeightDTO {
    public LocalDate date;
    public double weightKg;

    public WeightDTO(LocalDate date, double weightKg) {
        this.date = date;
        this.weightKg = weightKg;
    }
}
