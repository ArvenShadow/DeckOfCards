package edu.ntnu.idi.idatt2003.cards;

import edu.ntnu.idi.idatt2003.cardsfx.Rank;
import edu.ntnu.idi.idatt2003.cardsfx.Suit;
import java.util.HashMap;
import java.util.Map;

public class DeckOfCards {
  private Map<Integer, Card> cards = new HashMap<>();

  public DeckOfCards() {
    int index = 0;
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        cards.put(index, new Card(rank, suit));
        index++;
      }
    }
  }

  public Map<Integer, Card> getCards() {
    return cards;
  }
}