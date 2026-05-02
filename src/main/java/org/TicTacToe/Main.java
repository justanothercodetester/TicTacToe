package org.TicTacToe;

import atlantafx.base.theme.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage stage) throws Exception {

        Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            e.consume();
            close();
        });
        window = stage;
        stage.show();
    }

    public static void close() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Are you sure you wish to exit the program?");
        alert.setTitle("Confirmation");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.YES) {
            window.close();
        }
    }

    public static Stage getWindow() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
