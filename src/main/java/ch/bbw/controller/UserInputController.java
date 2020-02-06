package ch.bbw.controller;

import ch.bbw.models.Loaders;
import ch.bbw.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInputController {

    @FXML
    TextField player1Input;

    @FXML
    TextField player2Input;


    public void onCancel(ActionEvent actionEvent) {
        FXMLLoader loader = Loaders.mainLoader();
        Stage stage = (Stage) player1Input.getScene().getWindow();
        try {
            Pane pane = loader.load();
            stage.setScene(new Scene(pane,600, 350));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onStart(ActionEvent actionEvent) {
        if(!player1Input.getText().trim().equals("") && !player2Input.getText().trim().equals("")){
            Player player1 = new Player(player1Input.getText());
            Player player2 = new Player(player2Input.getText());

            FXMLLoader loader = Loaders.gameLoader();
            Stage stage = (Stage) player1Input.getScene().getWindow();
            try {
                Pane pane = loader.load();
                stage.setScene(new Scene(pane,700, 650));
                GameController c = loader.<GameController>getController();
                c.initPlayers(player1, player2);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
