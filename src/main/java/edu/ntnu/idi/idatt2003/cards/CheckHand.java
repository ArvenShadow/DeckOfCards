package edu.ntnu.idi.idatt2003.cards;

import edu.ntnu.idi.idatt2003.cardsfx.Rank;
import edu.ntnu.idi.idatt2003.cardsfx.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class CheckHand {

  public enum HandRanking {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH
  }

  public static HandRanking checkHand(List<Card> hand) {
    List<Card> sortedHand = hand.stream()
      .sorted(Comparator.comparingInt(card -> card.getRank().ordinal()))
      .collect(Collectors.toList());

    boolean isFlush = hand.stream().allMatch(card -> card.getSuit() == hand.get(0).getSuit());

    boolean isStraight = isConsecutive(sortedHand);

    Map<Rank, Long> rankCounts = hand.stream()
      .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

    Collection<Long> counts = rankCounts.values();

    if (isStraight && isFlush) return HandRanking.STRAIGHT_FLUSH;
    if (counts.contains(4L)) return HandRanking.FOUR_OF_A_KIND;
    if (counts.contains(3L) && counts.contains(2L)) return HandRanking.FULL_HOUSE;
    if (isFlush) return HandRanking.FLUSH;
    if (isStraight) return HandRanking.STRAIGHT;
    if (counts.contains(3L)) return HandRanking.THREE_OF_A_KIND;
    if (Collections.frequency(counts, 2L) == 2) return HandRanking.TWO_PAIR;
    if (counts.contains(2L)) return HandRanking.ONE_PAIR;

    return HandRanking.HIGH_CARD;
  }

  private static boolean isConsecutive(List<Card> sortedHand) {
    for (int i = 1; i < sortedHand.size(); i++) {
      int previousRank = sortedHand.get(i - 1).getRank().ordinal();
      int currentRank = sortedHand.get(i).getRank().ordinal();
      if (currentRank != previousRank + 1) {
        return false;
      }
    }
    return true;
  }

  public static int calculateHandValue(List<Card> hand) {
    if (hand == null) {
      return 0; // No cards, value is 0
    }
    return hand.stream().mapToInt(Card::getRankValue).sum();
  }

  public static int countHearts(List<Card> hand) {
    if (hand == null || hand.isEmpty()) {
      return 0; // No cards in the hand
    }
    return (int) hand.stream()
      .filter(card -> card.getSuit() == Suit.HEARTS)
      .count();
  }
}