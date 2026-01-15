package org.fitbuddy.service;

import org.fitbuddy.model.WeightPoint;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChartService {

    private final DataGateway gateway = new DummyGateway();

    public List<WeightPoint> getWeightLastNDays(int userId, int days) {
        List<WeightDTO> logs = gateway.getWeightLogsLastNDays(userId, days);
        List<WeightPoint> points = new ArrayList<>();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d");

        for (WeightDTO w : logs) {
            points.add(new WeightPoint(w.date.format(fmt), w.weightKg));
        }
        return points;
    }

    public int[] getWeeklyCalorieIntake(int userId) {
        return new int[]{1800, 1650, 1900, 1750, 1600};
    }

    public int[] getWeeklyCalorieBurned(int userId) {
        return new int[]{400, 500, 350, 450, 600};
    }

    public String[] getWeekLabels() {
        return new String[]{"Mon", "Tue", "Wed", "Thu", "Fri"};
    }
}
