package ch.bbw;

import ch.bbw.controller.UserInputController;
import ch.bbw.models.Loaders;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        FXMLLoader loader = Loaders.mainLoader();
        // loader.setController(new UserInputController());
        try {
            Parent pane = loader.load();
            var scene =  new Scene(pane, 600, 350);
            stage.setScene(scene);
            stage.show();
            System.out.println("start");
        } catch (IOException e) {
          e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }

}