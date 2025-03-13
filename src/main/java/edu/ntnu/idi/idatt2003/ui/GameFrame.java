package edu.ntnu.idi.idatt2003.ui;

import edu.ntnu.idi.idatt2003.cards.Card;
import edu.ntnu.idi.idatt2003.cards.DeckOfCards;
import edu.ntnu.idi.idatt2003.cards.Dealer;
import edu.ntnu.idi.idatt2003.cardsfx.CardFaceCreator;
import edu.ntnu.idi.idatt2003.cardsfx.Settings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class GameFrame {
  private final Stage stage;
  private final DeckOfCards deck;
  private final Dealer dealer;
  private HBox cardDisplay;

  public GameFrame(Stage stage) {
    this.stage = stage;
    this.deck = new DeckOfCards();
    this.dealer = new Dealer();
    initializeUI();
  }

  private void initializeUI() {
    cardDisplay = new HBox(10);
    cardDisplay.setPadding(new Insets(20));
    cardDisplay.setStyle("-fx-background-color: #c97dbe;");

    rerollCards();

    Button rerollButton = new Button("Deal Hand");
    rerollButton.setOnAction(e -> rerollCards());

    BorderPane root = new BorderPane();
    root.setCenter(cardDisplay);
    root.setBottom(rerollButton);
    BorderPane.setMargin(rerollButton, new Insets(10));


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
      List<Card> dealtCards = dealer.dealHand(deck, 5);

      for (Card card : dealtCards) {
        Node cardNode = CardFaceCreator.createFrontFace(
          card.getSuit(),
          card.getRank(),
          Settings.CARD_WIDTH,
          Settings.CARD_HEIGHT
        );
        cardDisplay.getChildren().add(cardNode);
      }
    } catch (IllegalArgumentException e) {
      cardDisplay.getChildren().clear();
    }
  }
}