package edu.ntnu.idi.idatt2003.cardsfx;

public enum Rank {

  ACE(1, "A"),
  _2(2, "2"),
  _3(3, "3"),
  _4(4, "4"),
  _5(5, "5"),
  _6(6, "6"),
  _7(7, "7"),
  _8(8, "8"),
  _9(9, "9"),
  _10(10, "10"),
  JACK(11, "J"),
  QUEEN(12, "Q"),
  KING(13, "K");

  private final int value; // Numeric value of the rank
  private final String name; // Display name of the rank

  Rank(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}