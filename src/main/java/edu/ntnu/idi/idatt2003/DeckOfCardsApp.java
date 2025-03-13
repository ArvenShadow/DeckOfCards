package edu.ntnu.idi.idatt2003;

import edu.ntnu.idi.idatt2003.ui.GameFrame;
import javafx.application.Application;
import javafx.stage.Stage;

public class DeckOfCardsApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    GameFrame gameFrame = new GameFrame(primaryStage);
    gameFrame.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}