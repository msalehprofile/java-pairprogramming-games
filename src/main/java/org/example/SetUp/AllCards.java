package org.example.SetUp;

import org.example.SetUp.CardType.Clubs;
import org.example.SetUp.CardType.Hearts;
import org.example.SetUp.CardType.Spades;
import org.example.SetUp.CardType.Diamonds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllCards {
    private final Rank[] allRanks = Rank.values();
    private static final List<Cards> allCards = new ArrayList<>();
    private static List<Cards> deckOfCards = allCards;
    public static Cards dealtCard;


    public void createAllCards() {
        for (Rank allRank : allRanks) {
            Clubs clubs = new Clubs(Suit.CLUBS, allRank);
            allCards.add(clubs);
        }

        for (Rank allRank : allRanks) {
            Hearts hearts = new Hearts(Suit.HEARTS, allRank);
            allCards.add(hearts);
        }

        for (Rank allRank : allRanks) {
            Spades spades = new Spades(Suit.SPADES, allRank);
            allCards.add(spades);
        }

        for (Rank allRank : allRanks) {
            Diamonds diamonds = new Diamonds(Suit.DIAMONDS, allRank);
            allCards.add(diamonds);
        }
    }

    public void sortDeckByValue() {
        deckOfCards = deckOfCards.stream()
                .sorted((a,b) -> a.getScore() - b.getScore())
                .collect(Collectors.toList());
    }

    public void sortDeckBySuit() {
        deckOfCards.sort((a, b) -> {
            if (a.getSuit() != b.getSuit()) {
                return a.getSuit().compareTo(b.getSuit());
            }
            return a.getScore() - b.getScore();
        });
    }

    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }



    public void dealCard() {
        dealtCard = deckOfCards.stream()
                .findFirst()
                .orElse(null);
        deckOfCards = deckOfCards.stream()
                .skip(1)
                .collect(Collectors.toList());
        assert dealtCard != null;
    }

    public void getCardVisual(Cards card) {
        char score;
        if( card.getScore() == 14) {
            score = 'A';
        } else if ( card.getScore() == 13) {
            score = 'K';
        } else if ( card.getScore() == 12) {
            score = 'Q';
        } else if ( card.getScore() == 11) {
            score = 'J';
        } else {
           score = (char) card.getScore();
        }
        String uniCode = "";
        if (card.getSuit().equals(Suit.HEARTS)) {
            uniCode = "♥";
        }
        if (card.getSuit().equals(Suit.DIAMONDS)) {
            uniCode = "♦";
        }
        if (card.getSuit().equals(Suit.CLUBS)) {
            uniCode = "♣";
        }
        if (card.getSuit().equals(Suit.SPADES)) {
            uniCode = "♠";
        }
        if(card.getScore() < 10) {
            System.out.print(" \n"
                    + " _____________\n"
                    + "| " + card.getScore() + "         " + card.getScore() + " |\n"
                    + "|             |\n"
                    + "|             |\n"
                    + "|      " + uniCode + "      |\n"
                    + "|             |\n"
                    + "| " + card.getScore() + "         " + card.getScore() + " |\n"
                    + "|_____________|");
        } else if (card.getScore() == 10) {
            System.out.print(" \n"
                    + " _____________\n"
                    + "| " + card.getScore() + "       " + card.getScore() + " |\n"
                    + "|             |\n"
                    + "|             |\n"
                    + "|      " + uniCode + "      |\n"
                    + "|             |\n"
                    + "| " + card.getScore() + "       " + card.getScore() + " |\n"
                    + "|_____________|");
        } else {
            System.out.print(" \n"
                    + " _____________\n"
                    + "| " + score + "         " + score + " |\n"
                    + "|             |\n"
                    + "|             |\n"
                    + "|      " + uniCode + "      |\n"
                    + "|             |\n"
                    + "| " + score + "         " + score + " |\n"
                    + "|_____________|");
        }
    }

    public List<Cards> getDeckOfCards() {
        return allCards;
    }


    public Cards getDealtCard() {
        return dealtCard;
    }
    public void resetDeck() {
        deckOfCards = allCards;
    }

    public void printCards() {
        System.out.println(deckOfCards);
    }
}
