package org.fitbuddy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.fitbuddy.util.AppContext;
import org.fitbuddy.util.CurrentSession;
import org.fitbuddy.util.SceneManager;
import org.fitbuddy.util.Validators;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    public void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void handleLogin() {
        String u = usernameField.getText();
        String p = passwordField.getText();

        if (Validators.isBlank(u)) { errorLabel.setText("⚠️ Enter username."); return; }
        if (Validators.isBlank(p)) { errorLabel.setText("⚠️ Enter password."); return; }

        int userId = AppContext.auth().login(u.trim(), p.trim());
        if (userId == -1) {
            errorLabel.setText("❌ Invalid login. Demo: admin / 1234");
            return;
        }

        CurrentSession.setUserId(userId);
        SceneManager.switchScene("Dashboard.fxml");
    }

    @FXML
    private void goToRegister() {
        SceneManager.switchScene("RegisterView.fxml");
    }
}
