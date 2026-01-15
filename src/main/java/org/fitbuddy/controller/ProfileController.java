package org.fitbuddy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.fitbuddy.util.SceneManager;

public class ProfileController {

    @FXML private Label nameLabel;
    @FXML private Label ageLabel;
    @FXML private Label heightLabel;
    @FXML private Label goalLabel;

    @FXML
    public void initialize() {
        // Demo values now — later from DB via UserDAO + CurrentSession
        nameLabel.setText("Name: Azmain Hasan");
        ageLabel.setText("Age: 20");
        heightLabel.setText("Height: 170 cm");
        goalLabel.setText("Goal: Maintain");
    }

    // Sidebar nav
    @FXML private void goToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
    @FXML private void goToWeight() { SceneManager.switchScene("Weight.fxml"); }
    @FXML private void goToCharts() { SceneManager.switchScene("Charts.fxml"); }
    @FXML private void backToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
}
