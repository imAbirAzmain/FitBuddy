package org.fitbuddy.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import org.fitbuddy.model.WeightPoint;
import org.fitbuddy.service.ChartService;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;

import java.util.List;

public class ChartsController {

    @FXML private LineChart<String, Number> weightChart;
    @FXML private BarChart<String, Number> calorieChart;

    @FXML private Label insightLabel;
    @FXML private Label coachLine1;
    @FXML private Label coachLine2;
    @FXML private Label coachLine3;

    private final ChartService service = new ChartService();

    @FXML
    public void initialize() {
        refreshCharts();
    }

    @FXML
    private void refreshCharts() {
        int userId = CurrentSession.getUserId();
        loadWeightChart(userId);
        loadCalorieChart(userId);
        updateInsight(userId);
        updateCoachPanel(userId);
    }

    private void loadWeightChart(int userId) {
        List<WeightPoint> points = service.getWeightLastNDays(userId, 7);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weight");

        for (WeightPoint p : points) {
            series.getData().add(new XYChart.Data<>(p.getDateLabel(), p.getWeight()));
        }

        weightChart.getData().clear();
        weightChart.getData().add(series);
    }

    private void loadCalorieChart(int userId) {
        String[] labels = service.getWeekLabels();
        int[] intakeVals = service.getWeeklyCalorieIntake(userId);
        int[] burnedVals = service.getWeeklyCalorieBurned(userId);

        XYChart.Series<String, Number> intake = new XYChart.Series<>();
        intake.setName("Intake");

        XYChart.Series<String, Number> burned = new XYChart.Series<>();
        burned.setName("Burned");

        for (int i = 0; i < labels.length; i++) {
            intake.getData().add(new XYChart.Data<>(labels[i], intakeVals[i]));
            burned.getData().add(new XYChart.Data<>(labels[i], burnedVals[i]));
        }

        calorieChart.getData().clear();
        calorieChart.getData().add(intake);
        calorieChart.getData().add(burned);
    }

    private void updateInsight(int userId) {
        List<WeightPoint> points = service.getWeightLastNDays(userId, 7);

        if (points == null || points.size() < 2) {
            insightLabel.setText("Insight: Not enough data");
            insightLabel.getStyleClass().setAll("tag", "tag-orange");
            return;
        }

        double first = points.get(0).getWeight();
        double last = points.get(points.size() - 1).getWeight();
        double diff = last - first;

        if (diff < -0.1) {
            insightLabel.setText("Insight: Weight decreasing ✅");
            insightLabel.getStyleClass().setAll("tag", "tag-green");
        } else if (diff > 0.1) {
            insightLabel.setText("Insight: Weight increasing ⚠️");
            insightLabel.getStyleClass().setAll("tag", "tag-orange");
        } else {
            insightLabel.setText("Insight: Weight stable ➖");
            insightLabel.getStyleClass().setAll("tag", "tag-blue");
        }
    }

    private void updateCoachPanel(int userId) {
        // Demo “coach” text (later can be driven by DB)
        coachLine1.setText("• Refresh updates charts instantly.");
        coachLine2.setText("• Aim for consistent sleep and hydration.");
        coachLine3.setText("• Track weight 3-4 times per week for accuracy.");
    }

    // Sidebar nav
    @FXML private void goToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
    @FXML private void goToWeight() { SceneManager.switchScene("Weight.fxml"); }
    @FXML private void goToProfile() { SceneManager.switchScene("Profile.fxml"); }
    @FXML private void backToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
}
