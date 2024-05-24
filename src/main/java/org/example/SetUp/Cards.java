package org.example.SetUp;

public class Cards {
    private final Suit suit;

    private final Rank rank;
    private final int score;

    public Cards(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.score = rank.getScore();
    }
    public Suit getSuit() {
        return suit;
    }
    public Rank getRank() {
        return rank;
    }
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return rank + " of " + suit + " (Score: " + score + ")";
    }
}
