package com.luanpaiva.jogodavelha;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/luanpaiva/jogodavelha/game-screen.fxml"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("JOGO DA VELHA v1.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}