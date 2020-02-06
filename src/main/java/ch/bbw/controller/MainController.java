package ch.bbw.controller;

import ch.bbw.models.Loaders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    Button button;

    @FXML
    public void onPlay(ActionEvent actionEvent) {
        FXMLLoader loader = Loaders.userInputLoader();
        Stage stage = (Stage) button.getScene().getWindow();
        try {
            Pane pane = loader.load();
            stage.setScene(new Scene(pane, 600, 350));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onHighscore(ActionEvent actionEvent) {
    }

    public void onQuit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
