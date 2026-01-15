package org.fitbuddy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fitbuddy.model.SleepRow;
import org.fitbuddy.util.AppContext;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;
import org.fitbuddy.util.Validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SleepController {

    @FXML private DatePicker datePicker;
    @FXML private TextField hoursField;
    @FXML private Label msgLabel;
    @FXML private TableView<SleepRow> table;

    private final ObservableList<SleepRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        msgLabel.setText("");

        int uid = CurrentSession.getUserId();
        rows.setAll(AppContext.logs().getSleeps(uid));
        table.setItems(rows);
    }

    @FXML
    private void addSleep() {
        int uid = CurrentSession.getUserId();
        LocalDate d = datePicker.getValue();
        String hText = hoursField.getText();

        if (d == null) { msgLabel.setText("⚠️ Pick a date."); return; }
        if (Validators.isBlank(hText)) { msgLabel.setText("⚠️ Enter hours."); return; }

        Double hours = Validators.parseDoubleSafe(hText);
        if (hours == null) { msgLabel.setText("⚠️ Hours must be a number."); return; }
        if (hours < 0 || hours > 24) { msgLabel.setText("⚠️ Hours out of range."); return; }

        String label = d.format(DateTimeFormatter.ofPattern("MMM d"));
        SleepRow row = new SleepRow(label, hours);

        AppContext.logs().addSleep(uid, row);
        rows.setAll(AppContext.logs().getSleeps(uid));

        hoursField.clear();
        msgLabel.setText("✅ Added.");
    }

    @FXML
    private void deleteSelected() {
        int uid = CurrentSession.getUserId();
        SleepRow selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) { msgLabel.setText("⚠️ Select a row to delete."); return; }

        AppContext.logs().deleteSleep(uid, selected);
        rows.setAll(AppContext.logs().getSleeps(uid));
        msgLabel.setText("🗑 Deleted.");
    }

    @FXML private void back() { SceneManager.switchScene("DailyLogView.fxml"); }
}
