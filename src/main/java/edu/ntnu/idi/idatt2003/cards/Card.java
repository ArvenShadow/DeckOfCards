package edu.ntnu.idi.idatt2003.cards;

import edu.ntnu.idi.idatt2003.cardsfx.Rank;
import edu.ntnu.idi.idatt2003.cardsfx.Suit;

public class Card {
  private Rank rank;
  private Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Rank getRank() {
    return rank;
  }
  public Suit getSuit() {
    return suit;
  }

  public int getRankValue() {
    return rank.getValue();
  }

  @Override
  public String toString() {
    return rank.getName() + " of " + suit.getName();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Card other = (Card) obj;
    return rank == other.rank && suit == other.suit;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((rank == null) ? 0 : rank.hashCode());
    result = prime * result + ((suit == null) ? 0 : suit.hashCode());
    return result;
  }
}
