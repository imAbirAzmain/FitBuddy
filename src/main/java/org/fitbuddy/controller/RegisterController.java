package org.fitbuddy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fitbuddy.util.AppContext;
import org.fitbuddy.util.SceneManager;
import org.fitbuddy.util.Validators;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private Label msgLabel;

    @FXML
    public void initialize() {
        msgLabel.setText("");
    }

    @FXML
    private void handleRegister() {
        String u = usernameField.getText();
        String p = passwordField.getText();
        String c = confirmField.getText();

        if (Validators.isBlank(u)) { msgLabel.setText("⚠️ Username required."); return; }
        if (Validators.isBlank(p) || p.trim().length() < 4) { msgLabel.setText("⚠️ Password must be 4+ chars."); return; }
        if (Validators.isBlank(c) || !p.trim().equals(c.trim())) { msgLabel.setText("⚠️ Passwords do not match."); return; }

        if (AppContext.auth().usernameExists(u.trim())) {
            msgLabel.setText("⚠️ Username already taken.");
            return;
        }

        boolean ok = AppContext.auth().register(u.trim(), p.trim());
        msgLabel.setText(ok ? "✅ Registered! Now login." : "❌ Could not register.");
    }

    @FXML
    private void backToLogin() {
        SceneManager.switchScene("LoginView.fxml");
    }
}
