package ch.bbw.controller;

import ch.bbw.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class GameController {

    @FXML
    private Label player1_1;
    @FXML
    private Label player1_2;
    @FXML
    private Label player1_3;
    @FXML
    private Label player2_1;
    @FXML
    private Label player2_2;
    @FXML
    private Label player2_3;

    @FXML
    private Label currentPlayerLabel;


    @FXML
    private Button rollsButton;
    @FXML
    private Button playButton;


    @FXML
    private ImageView dice0;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;
    @FXML
    private ImageView dice3;
    @FXML
    private ImageView dice4;

    @FXML
    private Label player1LeftScore;
    @FXML
    private Label player1RigthScore;
    @FXML
    private Label player1TotalScore;
    @FXML
    private Label player1Bonus;

    @FXML
    private Label player2LeftScore;
    @FXML
    private Label player2RigthScore;
    @FXML
    private Label player2TotalScore;
    @FXML
    private Label player2Bonus;


    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private int rolls = 3;


    private Image diceImage1;
    private Image diceImage2;
    private Image diceImage3;
    private Image diceImage4;
    private Image diceImage5;
    private Image diceImage6;

    private HashMap<Image, Integer> valueFromImage = new HashMap<>();
    private HashMap<Integer, Image> diceImages = new HashMap<>();
    private HashMap<Integer, ImageView> imageViewWithPosition = new HashMap<>();
    private HashMap<Integer, Boolean> isFixWihtPosition = new HashMap<>();
    private List<Integer> selectedDices = new ArrayList<>();

    private Label selectedLabel = new Label();
    private boolean newRound = true;
    private boolean leftSide = true;

    public void initPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        player1_1.setText(player1.getName());
        player1_2.setText(player1.getName());
        player1_3.setText(player1.getName());
        player2_1.setText(player2.getName());
        player2_2.setText(player2.getName());
        player2_3.setText(player2.getName());

        Random random = new Random();
        int number = random.nextInt(2);

        if (number == 0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }

        this.setUpNewRound();
    }


    @FXML
    public void initialize() {
        try {
            diceImage1 = new Image(GameController.class.getResource("/img/dice-1-md.png").toURI().toString());
            diceImage2 = new Image(GameController.class.getResource("/img/dice-2-md.png").toURI().toString());
            diceImage3 = new Image(GameController.class.getResource("/img/dice-3-md.png").toURI().toString());
            diceImage4 = new Image(GameController.class.getResource("/img/dice-4-md.png").toURI().toString());
            diceImage5 = new Image(GameController.class.getResource("/img/dice-5-md.png").toURI().toString());
            diceImage6 = new Image(GameController.class.getResource("/img/dice-6-md.png").toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        diceImages.put(1, diceImage1);
        diceImages.put(2, diceImage2);
        diceImages.put(3, diceImage3);
        diceImages.put(4, diceImage4);
        diceImages.put(5, diceImage5);
        diceImages.put(6, diceImage6);

        valueFromImage.put(diceImage1, 1);
        valueFromImage.put(diceImage2, 2);
        valueFromImage.put(diceImage3, 3);
        valueFromImage.put(diceImage4, 4);
        valueFromImage.put(diceImage5, 5);
        valueFromImage.put(diceImage6, 6);


    }

    private void setUpNewRound() {
        System.out.println("setup");
        if (player1.getName().equals(currentPlayer.getName())) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

        currentPlayerLabel.setText(currentPlayer.getName());

        newRound = true;
        rolls = 3;
        rollsButton.setText("Rolls (" + rolls + ")");
        rollsButton.setDisable(false);
        playButton.setDisable(true);

        ColorAdjust colorAdjustLigther = new ColorAdjust();
        colorAdjustLigther.setBrightness(0);
        for (ImageView image : imageViewWithPosition.values()) {
            image.setEffect(colorAdjustLigther);
            image.setImage(null);
        }

        selectedDices.clear();

        selectedLabel.setStyle("-fx-background-color: lightgray");

        imageViewWithPosition.put(0, dice0);
        imageViewWithPosition.put(1, dice1);
        imageViewWithPosition.put(2, dice2);
        imageViewWithPosition.put(3, dice3);
        imageViewWithPosition.put(4, dice4);

        isFixWihtPosition.put(0, false);
        isFixWihtPosition.put(1, false);
        isFixWihtPosition.put(2, false);
        isFixWihtPosition.put(3, false);
        isFixWihtPosition.put(4, false);

    }


    public void onPlay(ActionEvent actionEvent) {
        if (!selectedLabel.getText().equals("")) {
            if (currentPlayer.getName().equals(player1.getName())) {
                if (leftSide) {
                    int score = Integer.parseInt(player1LeftScore.getText());
                    score += Integer.parseInt(selectedLabel.getText());
                    player1LeftScore.setText(String.valueOf(score));

                    if (Integer.parseInt(player1LeftScore.getText()) > 62) {
                        player1Bonus.setText("35");
                        int total = Integer.parseInt(player1TotalScore.getText());
                        total += 35;
                        player1TotalScore.setText(String.valueOf(total));
                    }
                } else {
                    int score = Integer.parseInt(player1RigthScore.getText());
                    score += Integer.parseInt(selectedLabel.getText());
                    player1RigthScore.setText(String.valueOf(score));
                }
                int score = Integer.parseInt(player1TotalScore.getText());
                score += Integer.parseInt(selectedLabel.getText());
                player1TotalScore.setText(String.valueOf(score));


            } else {
                if (leftSide) {
                    int score = Integer.parseInt(player2LeftScore.getText());
                    score += Integer.parseInt(selectedLabel.getText());
                    player2LeftScore.setText(String.valueOf(score));

                    if (Integer.parseInt(player2LeftScore.getText()) > 62) {
                        player2Bonus.setText("35");
                        int total = Integer.parseInt(player1TotalScore.getText());
                        total += 35;
                        player1TotalScore.setText(String.valueOf(total));
                    }
                } else {
                    int score = Integer.parseInt(player2RigthScore.getText());
                    score += Integer.parseInt(selectedLabel.getText());
                    player2RigthScore.setText(String.valueOf(score));
                }
                int score = Integer.parseInt(player2TotalScore.getText());
                score += Integer.parseInt(selectedLabel.getText());
                player2TotalScore.setText(String.valueOf(score));

            }
            setUpNewRound();
        }
    }

    public void onRoll(ActionEvent actionEvent) throws URISyntaxException {
        Random random = new Random();
        List<URL> fixDices = new ArrayList<>();


        for (int i = 0; i < 5 - fixDices.size(); i++) {
            int diceNumber = random.nextInt(6) + 1;
            Image image = diceImages.get(diceNumber);
            if (!isFixWihtPosition.get(i)) {
                imageViewWithPosition.get(i).setImage(image);
            }
        }

        rolls--;
        rollsButton.setText("Roll (" + rolls + ")");
        if (rolls == 0) {
            rollsButton.setDisable(true);
        }
    }

    public void onDice(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        int index = Character.getNumericValue(source.getId().charAt(4));
        boolean isFixed = isFixWihtPosition.get(index);
        isFixWihtPosition.put(index, !isFixed);

        ColorAdjust colorAdjustDarker = new ColorAdjust();
        colorAdjustDarker.setBrightness(-0.5);

        ColorAdjust colorAdjustLigther = new ColorAdjust();
        colorAdjustLigther.setBrightness(0);

        if (!isFixed) {
            source.setEffect(colorAdjustDarker);
            selectedDices.add(getValueFromImage(getImageFromId(source.getId())));
        } else {
            selectedDices.remove((Object) getValueFromImage(getImageFromId(source.getId())));
            source.setEffect(colorAdjustLigther);
        }
        if (selectedDices.size() == 5) {
            playButton.setDisable(false);
        } else {
            playButton.setDisable(true);
        }
    }


    public void onAces(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        int playerNumberFromId = Character.getNumericValue(label.getId().charAt(1));
        if (playerNumberFromId == 1 && currentPlayer.getName().equals(player1.getName()) || playerNumberFromId == 2 && currentPlayer.getName().equals(player2.getName())) {

            if (!newRound) {
                selectedLabel.setStyle("-fx-background-color: lightgray");
                selectedLabel.setText("");
            }

            if (label.getText().equals("")) {


                selectedLabel = label;

                selectedLabel.setStyle("-fx-background-color: grey");
                int values = 0;
                for (int value : selectedDices) {
                    if (value == 1) {
                        values += value;
                    }
                }
                selectedLabel.setText(String.valueOf(values));
                leftSide = true;
                newRound = false;

            }
        }
    }

    public void onTwos(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        int playerNumberFromId = Character.getNumericValue(label.getId().charAt(1));
        if (playerNumberFromId == 1 && currentPlayer.getName().equals(player1.getName()) || playerNumberFromId == 2 && currentPlayer.getName().equals(player2.getName())) {
            if (!newRound) {
                selectedLabel.setStyle("-fx-background-color: lightgray");
                selectedLabel.setText("");
            }

            if (label.getText().equals("")) {

                selectedLabel = label;
                selectedLabel.setStyle("-fx-background-color: grey");
                int values = 0;
                for (int value : selectedDices) {
                    if (value == 2) {
                        values += value;
                    }
                }
                selectedLabel.setText(String.valueOf(values));

                leftSide = true;
                newRound = false;
            }
        }
    }

    public void onThrees(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        int playerNumberFromId = Character.getNumericValue(label.getId().charAt(1));
        if (playerNumberFromId == 1 && currentPlayer.getName().equals(player1.getName()) || playerNumberFromId == 2 && currentPlayer.getName().equals(player2.getName())) {
            if (!newRound) {
                selectedLabel.setStyle("-fx-background-color: lightgray");
                selectedLabel.setText("");
            }

            if (label.getText().equals("")) {

                selectedLabel = label;
                selectedLabel.setStyle("-fx-background-color: grey");
                int values = 0;
                for (int value : selectedDices) {
                    if (value == 3) {
                        values += value;
                    }
                }
                selectedLabel.setText(String.valueOf(values));

                leftSide = true;
                newRound = false;
            }
        }
    }

    public void onFours(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        int playerNumberFromId = Character.getNumericValue(label.getId().charAt(1));
        if (playerNumberFromId == 1 && currentPlayer.getName().equals(player1.getName()) || playerNumberFromId == 2 && currentPlayer.getName().equals(player2.getName())) {
            if (!newRound) {
                selectedLabel.setStyle("-fx-background-color: lightgray");
                selectedLabel.setText("");
            }

            if (label.getText().equals("")) {

                selectedLabel = label;
                selectedLabel.setStyle("-fx-background-color: grey");
                int values = 0;
                for (int value : selectedDices) {
                    if (value == 4) {
                        values += value;
                    }
                }
                selectedLabel.setText(String.valueOf(values));

                leftSide = true;
                newRound = false;
            }
        }
    }

    public void onFives(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        int playerNumberFromId = Character.getNumericValue(label.getId().charAt(1));
        if (playerNumberFromId == 1 && currentPlayer.getName().equals(player1.getName()) || playerNumberFromId == 2 && currentPlayer.getName().equals(player2.getName())) {
            if (!newRound) {
                selectedLabel.setStyle("-fx-background-color: lightgray");
                selectedLabel.setText("");
            }
            if (label.getText().equals("")) {

                selectedLabel = label;
                selectedLabel.setStyle("-fx-background-color: grey");
                int values = 0;
                for (int value : selectedDices) {
                    if (value == 5) {
                        values += value;
                    }
                }
                selectedLabel.setText(String.valueOf(values));

                leftSide = true;
                newRound = false;
            }
        }
    }

    public void onSixes(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        int playerNumberFromId = Character.getNumericValue(label.getId().charAt(1));
        if (playerNumberFromId == 1 && currentPlayer.getName().equals(player1.getName()) || playerNumberFromId == 2 && currentPlayer.getName().equals(player2.getName())) {
            if (!newRound) {
                selectedLabel.setStyle("-fx-background-color: lightgray");
                selectedLabel.setText("");
            }

            if (label.getText().equals("")) {

                selectedLabel = label;
                selectedLabel.setStyle("-fx-background-color: grey");
                int values = 0;
                for (int value : selectedDices) {
                    if (value == 6) {
                        values += value;
                    }
                }
                selectedLabel.setText(String.valueOf(values));

                leftSide = true;
                newRound = false;
            }
        }
    }

    public void onThreeOfKind(MouseEvent mouseEvent) {
    }

    public void onFourOfKind(MouseEvent mouseEvent) {
    }

    public void onFullHouse(MouseEvent mouseEvent) {
    }

    public void onSmallStraight(MouseEvent mouseEvent) {
    }

    public void onLargeStraight(MouseEvent mouseEvent) {
    }

    public void onYatzii(MouseEvent mouseEvent) {
    }

    public void onChance(MouseEvent mouseEvent) {
    }


    public int getValueFromImage(Image image) {
        return valueFromImage.get(image);
    }

    public Image getImageFromId(String id) {
        switch (id) {
            case "dice0":
                return this.dice0.getImage();
            case "dice1":
                return this.dice1.getImage();
            case "dice2":
                return this.dice2.getImage();
            case "dice3":
                return this.dice3.getImage();
            case "dice4":
                return this.dice4.getImage();
        }
        return null;
    }

}
