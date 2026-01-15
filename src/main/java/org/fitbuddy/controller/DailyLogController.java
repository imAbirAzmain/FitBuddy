package org.fitbuddy.controller;

import javafx.fxml.FXML;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;

public class DailyLogController {

    @FXML private void goSteps() { SceneManager.switchScene("StepsView.fxml"); }
    @FXML private void goMeals() { SceneManager.switchScene("MealsView.fxml"); }
    @FXML private void goWorkouts() { SceneManager.switchScene("WorkoutsView.fxml"); }
    @FXML private void goSleep() { SceneManager.switchScene("SleepView.fxml"); }

    @FXML private void backToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }

    @FXML
    private void logout() {
        CurrentSession.clear();
        SceneManager.switchScene("LoginView.fxml");
    }
}
