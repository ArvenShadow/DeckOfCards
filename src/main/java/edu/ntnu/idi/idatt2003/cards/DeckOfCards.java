package edu.ntnu.idi.idatt2003.cards;

import edu.ntnu.idi.idatt2003.cardsfx.Rank;
import edu.ntnu.idi.idatt2003.cardsfx.Suit;
import java.util.HashMap;
import java.util.List;
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

  public void removeCards(List<Card> cardsToRemove) {
    cardsToRemove.forEach(card -> cards.values().remove(card));
  }

  public void shuffle() {
    cards.clear();
    initializeDeck();
  }

  private void initializeDeck() {
    int index = 0;
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        cards.put(index, new Card(rank, suit));
        index++;
      }
    }
  }
}
