package org.example.Games.Poker;

public enum HandRank {
    HIGH_CARD("High Card"),
    ONE_PAIR("One Pair"),
    TWO_PAIR("Two Pair"),
    THREE_OF_A_KIND("Three of a Kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    FOUR_OF_A_KIND("Four of a Kind"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_FLUSH("Royal Flush");

    private final String name;

    HandRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HandRank fromString(String name) {
        for (HandRank rank : HandRank.values()) {
            if (rank.name.equalsIgnoreCase(name)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("No enum constant for name: " + name);
    }
}
