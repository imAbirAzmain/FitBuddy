package org.fitbuddy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fitbuddy.model.WeightRow;
import org.fitbuddy.util.SceneManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeightController {

    @FXML private Label modeLabel;
    @FXML private Label messageLabel;

    @FXML private Label latestLabel;
    @FXML private Label avgLabel;
    @FXML private Label diffLabel;

    @FXML private ProgressBar progressBar;

    @FXML private DatePicker datePicker;
    @FXML private TextField weightField;

    @FXML private TableView<WeightRow> weightTable;

    private final ObservableList<WeightRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        modeLabel.setText("Mode: Demo Data (DB later)");
        datePicker.setValue(LocalDate.now());

        // Demo history
        rows.add(new WeightRow("Jan 10", 70.0));
        rows.add(new WeightRow("Jan 11", 69.8));
        rows.add(new WeightRow("Jan 12", 69.6));
        rows.add(new WeightRow("Jan 13", 69.5));
        rows.add(new WeightRow("Jan 14", 69.3));

        weightTable.setItems(rows);
        updateQuickStats();
    }

    @FXML
    private void addWeight() {
        LocalDate date = datePicker.getValue();
        String wText = weightField.getText().trim();

        if (date == null) {
            messageLabel.setText("⚠️ Please pick a date.");
            return;
        }
        if (wText.isEmpty()) {
            messageLabel.setText("⚠️ Please enter weight.");
            return;
        }

        double w;
        try {
            w = Double.parseDouble(wText);
        } catch (Exception e) {
            messageLabel.setText("⚠️ Weight must be a number (example: 69.5).");
            return;
        }

        if (w <= 0 || w > 400) {
            messageLabel.setText("⚠️ Please enter a realistic weight.");
            return;
        }

        String label = date.format(DateTimeFormatter.ofPattern("MMM d"));
        rows.add(new WeightRow(label, w));
        weightField.clear();

        messageLabel.setText("✅ Added: " + label + " → " + w + " kg");
        updateQuickStats();
    }

    @FXML
    private void deleteSelected() {
        WeightRow selected = weightTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("⚠️ Select a row to delete.");
            return;
        }
        rows.remove(selected);
        messageLabel.setText("🗑 Deleted entry.");
        updateQuickStats();
    }

    private void updateQuickStats() {
        if (rows.isEmpty()) {
            latestLabel.setText("Latest: -- kg");
            avgLabel.setText("Average (5): -- kg");
            diffLabel.setText("Change: -- kg");
            progressBar.setProgress(0);
            return;
        }

        double latest = rows.get(rows.size() - 1).getWeight();
        latestLabel.setText(String.format("Latest: %.1f kg", latest));

        int start = Math.max(0, rows.size() - 5);
        double sum = 0;
        for (int i = start; i < rows.size(); i++) sum += rows.get(i).getWeight();
        double avg = sum / (rows.size() - start);
        avgLabel.setText(String.format("Average (5): %.1f kg", avg));

        double first = rows.get(start).getWeight();
        double diff = latest - first;
        diffLabel.setText(String.format("Change: %.1f kg", diff));

        // demo progress bar mapping (0..120kg scale)
        double p = Math.min(1.0, Math.max(0.0, latest / 120.0));
        progressBar.setProgress(p);
    }

    // Sidebar nav
    @FXML private void goToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
    @FXML private void goToCharts() { SceneManager.switchScene("Charts.fxml"); }
    @FXML private void goToProfile() { SceneManager.switchScene("Profile.fxml"); }
    @FXML private void backToDashboard() { SceneManager.switchScene("Dashboard.fxml"); }
}
