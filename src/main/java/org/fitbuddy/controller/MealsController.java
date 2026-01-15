package org.fitbuddy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fitbuddy.model.MealRow;
import org.fitbuddy.util.AppContext;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;
import org.fitbuddy.util.Validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MealsController {

    @FXML private DatePicker datePicker;
    @FXML private TextField mealNameField;
    @FXML private TextField caloriesField;
    @FXML private Label msgLabel;
    @FXML private TableView<MealRow> table;

    private final ObservableList<MealRow> rows = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        msgLabel.setText("");

        int uid = CurrentSession.getUserId();
        rows.setAll(AppContext.logs().getMeals(uid));
        table.setItems(rows);
    }

    @FXML
    private void addMeal() {
        int uid = CurrentSession.getUserId();
        LocalDate d = datePicker.getValue();
        String name = mealNameField.getText();
        String calText = caloriesField.getText();

        if (d == null) { msgLabel.setText("⚠️ Pick a date."); return; }
        if (Validators.isBlank(name)) { msgLabel.setText("⚠️ Enter meal name."); return; }
        if (Validators.isBlank(calText)) { msgLabel.setText("⚠️ Enter calories."); return; }

        Integer cal = Validators.parseIntSafe(calText);
        if (cal == null) { msgLabel.setText("⚠️ Calories must be a number."); return; }
        if (cal < 0 || cal > 10000) { msgLabel.setText("⚠️ Calories out of range."); return; }

        String label = d.format(DateTimeFormatter.ofPattern("MMM d"));
        MealRow row = new MealRow(label, name.trim(), cal);

        AppContext.logs().addMeal(uid, row);
        rows.setAll(AppContext.logs().getMeals(uid));

        mealNameField.clear();
        caloriesField.clear();
        msgLabel.setText("✅ Added.");
    }

    @FXML
    private void deleteSelected() {
        int uid = CurrentSession.getUserId();
        MealRow selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) { msgLabel.setText("⚠️ Select a row to delete."); return; }

        AppContext.logs().deleteMeal(uid, selected);
        rows.setAll(AppContext.logs().getMeals(uid));
        msgLabel.setText("🗑 Deleted.");
    }

    @FXML private void back() { SceneManager.switchScene("DailyLogView.fxml"); }
}
