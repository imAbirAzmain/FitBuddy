package org.fitbuddy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.fitbuddy.service.DashboardService;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DashboardController {

    @FXML private Label bmiValue;
    @FXML private Label weightValue;
    @FXML private Label caloriesValue;
    @FXML private Label statusLabel;

    @FXML private Label bmiNote;
    @FXML private Label weightNote;
    @FXML private Label todayTag;

    @FXML private Label goalLabel;
    @FXML private Label goalSubLabel;

    // NEW progress bars
    @FXML private ProgressBar bmiProgress;
    @FXML private ProgressBar weightProgress;
    @FXML private ProgressBar calorieProgress;

    private final DashboardService service = new DashboardService();

    @FXML
    public void initialize() {
        statusLabel.setText("Mode: Demo Data (DB not connected yet)");

        todayTag.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM d")));

        int userId = CurrentSession.getUserId();

        double bmi = service.getBmi(userId);
        double weight = service.getLatestWeight(userId);
        int intake = service.getCaloriesIntakeToday(userId);
        int burned = service.getCaloriesBurnedToday(userId);

        bmiValue.setText(String.format("%.1f", bmi));
        weightValue.setText(String.format("%.1f", weight));
        caloriesValue.setText(intake + " / " + burned);

        // BMI status
        String bmiStatus;
        if (bmi == 0) bmiStatus = "--";
        else if (bmi < 18.5) bmiStatus = "Underweight";
        else if (bmi < 25) bmiStatus = "Normal ✅";
        else if (bmi < 30) bmiStatus = "Overweight ⚠️";
        else bmiStatus = "Obese ⚠️";

        bmiNote.setText("Status: " + bmiStatus);

        // Weight trend (demo)
        weightNote.setText("Trend: Stable ➖");

        // Goal text
        goalLabel.setText("Maintain Fitness");
        goalSubLabel.setText("Stay consistent • Water • Sleep");

        // Progress Bars (demo logic)
        // BMI: normalize to 0..1 based on 0..40 range
        double bmiP = Math.min(1.0, Math.max(0.0, bmi / 40.0));
        bmiProgress.setProgress(bmiP);

        // Weight: fake progress around 50-65% (demo)
        weightProgress.setProgress(0.55);

        // Calories: intake vs 2500 (demo)
        double calP = Math.min(1.0, Math.max(0.0, intake / 2500.0));
        calorieProgress.setProgress(calP);
    }

    // Sidebar navigation
    @FXML private void goToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
    @FXML private void goToWeight() { SceneManager.switchScene("Weight.fxml"); }
    @FXML private void goToCharts() { SceneManager.switchScene("Charts.fxml"); }
    @FXML private void goToProfile() { SceneManager.switchScene("Profile.fxml"); }
    @FXML private void goToDailyLogView() { SceneManager.switchScene("DailyLogView.fxml"); }
    @FXML
    private void logout() {
        CurrentSession.clear();
        SceneManager.switchScene("LoginView.fxml");
    }
}
