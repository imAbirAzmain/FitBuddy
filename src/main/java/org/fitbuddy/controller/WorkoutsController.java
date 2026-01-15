package org.fitbuddy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fitbuddy.model.WorkoutRow;
import org.fitbuddy.util.AppContext;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;
import org.fitbuddy.util.Validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkoutsController {

    @FXML private DatePicker datePicker;
    @FXML private TextField workoutField;
    @FXML private TextField minutesField;
    @FXML private TextField burnedField;
    @FXML private Label msgLabel;
    @FXML private TableView<WorkoutRow> table;

    private final ObservableList<WorkoutRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        msgLabel.setText("");

        int uid = CurrentSession.getUserId();
        rows.setAll(AppContext.logs().getWorkouts(uid));
        table.setItems(rows);
    }

    @FXML
    private void addWorkout() {
        int uid = CurrentSession.getUserId();
        LocalDate d = datePicker.getValue();
        String name = workoutField.getText();
        String minText = minutesField.getText();
        String calText = burnedField.getText();

        if (d == null) { msgLabel.setText("⚠️ Pick a date."); return; }
        if (Validators.isBlank(name)) { msgLabel.setText("⚠️ Enter workout name."); return; }

        Integer mins = Validators.parseIntSafe(minText);
        Integer cals = Validators.parseIntSafe(calText);

        if (mins == null || cals == null) { msgLabel.setText("⚠️ Minutes & calories must be numbers."); return; }
        if (mins < 0 || mins > 600) { msgLabel.setText("⚠️ Minutes out of range."); return; }
        if (cals < 0 || cals > 5000) { msgLabel.setText("⚠️ Calories out of range."); return; }

        String label = d.format(DateTimeFormatter.ofPattern("MMM d"));
        WorkoutRow row = new WorkoutRow(label, name.trim(), mins, cals);

        AppContext.logs().addWorkout(uid, row);
        rows.setAll(AppContext.logs().getWorkouts(uid));

        workoutField.clear();
        minutesField.clear();
        burnedField.clear();
        msgLabel.setText("✅ Added.");
    }

    @FXML
    private void deleteSelected() {
        int uid = CurrentSession.getUserId();
        WorkoutRow selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) { msgLabel.setText("⚠️ Select a row to delete."); return; }

        AppContext.logs().deleteWorkout(uid, selected);
        rows.setAll(AppContext.logs().getWorkouts(uid));
        msgLabel.setText("🗑 Deleted.");
    }

    @FXML private void back() { SceneManager.switchScene("DailyLogView.fxml"); }
}
