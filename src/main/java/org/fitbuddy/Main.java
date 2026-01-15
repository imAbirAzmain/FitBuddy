package org.fitbuddy;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fitbuddy.util.SceneManager;

import javax.imageio.ImageIO;
import java.awt.Taskbar;
import java.awt.image.BufferedImage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // 1) Window title
        primaryStage.setTitle("FitBuddy");

        // 2) Fixed premium size (you can tweak)
        primaryStage.setWidth(1120);
        primaryStage.setHeight(720);
        primaryStage.setMinWidth(1120);
        primaryStage.setMinHeight(720);
        primaryStage.setResizable(true);

        // 3) JavaFX window icon (and sometimes mac title icon)
        try {
            Image fxIcon = new Image(getClass().getResourceAsStream("/images/fitbuddy.png"));
            primaryStage.getIcons().add(fxIcon);
        } catch (Exception e) {
            System.out.println("JavaFX icon not found: /images/fitbuddy.png");
        }

        // 4) Force mac Dock icon (best)
        try {
            if (Taskbar.isTaskbarSupported()) {
                Taskbar taskbar = Taskbar.getTaskbar();
                BufferedImage awtIcon = ImageIO.read(getClass().getResource("/images/fitbuddy.png"));
                taskbar.setIconImage(awtIcon);
            }
        } catch (Exception e) {
            System.out.println("Dock icon set failed: " + e.getMessage());
        }

        // 5) Show Splash first (2 seconds)
        Scene splashScene = buildSplashScene();
        primaryStage.setScene(splashScene);
        primaryStage.centerOnScreen();
        primaryStage.show();

        // 6) After 2 seconds → open Dashboard
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            // connect stage to SceneManager AFTER splash shows
            SceneManager.setStage(primaryStage);
            SceneManager.switchScene("LoginView.fxml");
            primaryStage.centerOnScreen();
        });
        delay.play();
    }

    private Scene buildSplashScene() {
        VBox root = new VBox(14);
        root.getStyleClass().add("page");
        root.setStyle("-fx-alignment: center; -fx-padding: 40;");

        Label title = new Label("FitBuddy");
        title.getStyleClass().add("h1");

        Label sub = new Label("Neon Fitness Tracker • Loading...");
        sub.getStyleClass().add("muted");

        ProgressIndicator pi = new ProgressIndicator();
        pi.setPrefSize(46, 46);

        root.getChildren().addAll(title, sub, pi);

        Scene scene = new Scene(root, 1120, 720);

        // add CSS (same as all pages)
        scene.getStylesheets().add(getClass().getResource("/style/app.css").toExternalForm());

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}