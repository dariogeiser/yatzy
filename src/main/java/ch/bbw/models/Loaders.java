package ch.bbw.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Loaders {
    public static FXMLLoader mainLoader() {
            return new FXMLLoader(Loaders.class.getResource("/view/main.fxml"));
    }

    public static FXMLLoader userInputLoader() {
            return new FXMLLoader(Loaders.class.getResource("/view/userInput.fxml"));
    }

    public static FXMLLoader gameLoader() {
            return new FXMLLoader(Loaders.class.getResource("/view/game.fxml"));
    }
}
