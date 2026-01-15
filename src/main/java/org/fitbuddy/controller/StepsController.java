package org.fitbuddy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fitbuddy.model.StepsRow;
import org.fitbuddy.util.AppContext;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;
import org.fitbuddy.util.Validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StepsController {

    @FXML private DatePicker datePicker;
    @FXML private TextField stepsField;
    @FXML private Label msgLabel;
    @FXML private TableView<StepsRow> table;

    private final ObservableList<StepsRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        msgLabel.setText("");

        int uid = CurrentSession.getUserId();
        rows.setAll(AppContext.logs().getSteps(uid));
        table.setItems(rows);
    }

    @FXML
    private void addSteps() {
        int uid = CurrentSession.getUserId();
        LocalDate d = datePicker.getValue();
        String s = stepsField.getText();

        if (d == null) { msgLabel.setText("⚠️ Pick a date."); return; }
        if (Validators.isBlank(s)) { msgLabel.setText("⚠️ Enter steps."); return; }

        Integer steps = Validators.parseIntSafe(s);
        if (steps == null) { msgLabel.setText("⚠️ Steps must be a number."); return; }
        if (steps < 0 || steps > 200000) { msgLabel.setText("⚠️ Steps out of range."); return; }

        String label = d.format(DateTimeFormatter.ofPattern("MMM d"));
        StepsRow row = new StepsRow(label, steps);

        AppContext.logs().addSteps(uid, row);
        rows.setAll(AppContext.logs().getSteps(uid));
        stepsField.clear();
        msgLabel.setText("✅ Added.");
    }

    @FXML
    private void deleteSelected() {
        int uid = CurrentSession.getUserId();
        StepsRow selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) { msgLabel.setText("⚠️ Select a row to delete."); return; }

        AppContext.logs().deleteSteps(uid, selected);
        rows.setAll(AppContext.logs().getSteps(uid));
        msgLabel.setText("🗑 Deleted.");
    }

    @FXML private void back() { SceneManager.switchScene("DailyLogView.fxml"); }
}
