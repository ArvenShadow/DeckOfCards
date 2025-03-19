package edu.ntnu.idi.idatt2003.ui;

import edu.ntnu.idi.idatt2003.cards.Card;
import edu.ntnu.idi.idatt2003.cards.DeckOfCards;
import edu.ntnu.idi.idatt2003.cards.Dealer;
import edu.ntnu.idi.idatt2003.cards.CheckHand;
import edu.ntnu.idi.idatt2003.cardsfx.CardFaceCreator;
import edu.ntnu.idi.idatt2003.cardsfx.Settings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class GameFrame {
  private final Stage stage;
  private final DeckOfCards deck;
  private final Dealer dealer;
  private VBox buttonBox;
  private HBox cardDisplay;
  private Label infoBox;
  private List<Card> currentHand;
  private Label cardValueBox;

  public GameFrame(Stage stage) {
    this.stage = stage;
    this.deck = new DeckOfCards();
    this.dealer = new Dealer();
    initializeUI();
  }

  private void initializeUI() {
    // CENTER: Card display
    cardDisplay = new HBox(20);
    cardDisplay.setPadding(new Insets(30));
    cardDisplay.setStyle("-fx-background-color: #c97dbe;");

    // LEFT: Button box
    buttonBox = new VBox(20);
    buttonBox.setPadding(new Insets(30));
    buttonBox.setStyle("-fx-background-color: #d8bfd8;");

    Button shuffleButton = new Button("Shuffle");
    shuffleButton.setOnAction(e -> shuffleDeck());
    buttonBox.getChildren().add(shuffleButton);

    cardValueBox = new Label("Value of current hand: 0");
    cardValueBox.setPadding(new Insets(10));
    cardValueBox.setStyle("-fx-background-color: #c2f0c2; -fx-font-size: 14px;");

    Button rerollButton = new Button("Deal Hand");
    rerollButton.setOnAction(e -> rerollCards());

    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setOnAction(e -> checkAndDisplayHandRanking());

    cardValueBox = new Label("Value of current hand: 0");
    cardValueBox.setPadding(new Insets(10));
    cardValueBox.setStyle("-fx-background-color: #c2f0c2; -fx-font-size: 14px;");

    buttonBox.getChildren().addAll(rerollButton, checkHandButton, cardValueBox);

    // BOTTOM: Info box
    infoBox = new Label("Game Info and Hand Rankings will appear here.");
    infoBox.setPadding(new Insets(10));
    infoBox.setStyle("-fx-background-color: #f4c2c2; -fx-font-size: 14px;");


    BorderPane root = new BorderPane();
    root.setCenter(cardDisplay);
    root.setLeft(buttonBox);
    root.setBottom(infoBox);


    Scene scene = new Scene(root, 800, 400, Color.GREEN);
    stage.setScene(scene);
    stage.setTitle("Card Game");
  }

  public void show() {
    stage.show();
  }

  private void rerollCards() {
    cardDisplay.getChildren().clear();
    try {
      currentHand = dealer.dealHand(deck, 5);

      for (Card card : currentHand) {
        Node cardNode = CardFaceCreator.createFrontFace(
          card.getSuit(),
          card.getRank(),
          Settings.CARD_WIDTH,
          Settings.CARD_HEIGHT
        );
        cardDisplay.getChildren().add(cardNode);
      }

      int handValue = CheckHand.calculateHandValue(currentHand);
      cardValueBox.setText("Value of current hand: " + handValue);


      infoBox.setText("New hand dealt. Check the hand rank using the left panel.");
    } catch (IllegalArgumentException e) {
      cardDisplay.getChildren().clear();
      infoBox.setText("Not enough cards in the deck to deal a new hand.");
    }
  }

  private void checkAndDisplayHandRanking() {
    if (currentHand == null || currentHand.isEmpty()) {
      // Show an alert if no cards are dealt
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Hand");
      alert.setHeaderText(null);
      alert.setContentText("No hand is currently dealt. Please deal a hand first.");
      alert.showAndWait();
      return;
    }

    CheckHand.HandRanking handRanking = CheckHand.checkHand(currentHand);


    infoBox.setText("Your hand is ranked as: " + handRanking);
  }

  private void shuffleDeck() {
    deck.shuffle();
    cardDisplay.getChildren().clear();
    infoBox.setText("Deck shuffled and reset.");
    cardValueBox.setText("Value of current hand: 0");
    currentHand = null;
  }
}