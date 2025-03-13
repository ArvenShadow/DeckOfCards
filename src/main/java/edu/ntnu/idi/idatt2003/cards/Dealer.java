package edu.ntnu.idi.idatt2003.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dealer {
  private final Random random = new Random();

  public List<Card> dealHand(DeckOfCards deck, int n) {
    List<Card> hand = new ArrayList<>();
    Map<Integer, Card> cards = deck.getCards();
    List<Integer> indexes = new ArrayList<>(cards.keySet());

    if (n > indexes.size()) {
      throw new IllegalArgumentException("Not enough cards in deck");
    }

    for (int i = 0; i < n; i++) {
      int randomIndex = random.nextInt(indexes.size());
      hand.add(cards.get(indexes.get(randomIndex)));
    }
    return hand;
  }
}
