package org.fitbuddy.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage stage;

    private SceneManager() {}

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    SceneManager.class.getResource("/view/" + fxmlFile)
            );

            Scene scene = new Scene(loader.load());

            // Apply global CSS theme
            scene.getStylesheets().add(
                    SceneManager.class.getResource("/style/app.css").toExternalForm()
            );

            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
