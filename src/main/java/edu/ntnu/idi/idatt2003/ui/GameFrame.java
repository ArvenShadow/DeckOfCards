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
  private VBox buttonBox; // Left section for buttons
  private HBox cardDisplay; // Center section for displaying cards
  private Label infoBox; // Bottom section for check hand info
  private List<Card> currentHand; // To track the current dealt hand

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
    cardDisplay.setStyle("-fx-background-color: #c97dbe;"); // Adds card background style

    // LEFT: Button box
    buttonBox = new VBox(20);
    buttonBox.setPadding(new Insets(30));
    buttonBox.setStyle("-fx-background-color: #d8bfd8;"); // Light purple background

    Button rerollButton = new Button("Deal Hand");
    rerollButton.setOnAction(e -> rerollCards());

    Button checkHandButton = new Button("Check Hand");
    checkHandButton.setOnAction(e -> checkAndDisplayHandRanking());

    buttonBox.getChildren().addAll(rerollButton, checkHandButton);

    // BOTTOM: Info box
    infoBox = new Label("Game Info and Hand Rankings will appear here.");
    infoBox.setPadding(new Insets(10));
    infoBox.setStyle("-fx-background-color: #f4c2c2; -fx-font-size: 14px;"); // Light pink background with text style

    // ROOT LAYOUT
    BorderPane root = new BorderPane();
    root.setCenter(cardDisplay); // Cards displayed in the center
    root.setLeft(buttonBox); // Button box placed on the left
    root.setBottom(infoBox); // Info box placed at the bottom

    // Setup the scene and stage
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
      currentHand = dealer.dealHand(deck, 5); // Deal a new hand of 5 cards

      for (Card card : currentHand) {
        Node cardNode = CardFaceCreator.createFrontFace(
          card.getSuit(),
          card.getRank(),
          Settings.CARD_WIDTH,
          Settings.CARD_HEIGHT
        );
        cardDisplay.getChildren().add(cardNode);
      }

      // Update game info
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

    // Check the hand ranking
    CheckHand.HandRanking handRanking = CheckHand.checkHand(currentHand);

    // Update the game info at the bottom
    infoBox.setText("Your hand is ranked as: " + handRanking);
  }
}