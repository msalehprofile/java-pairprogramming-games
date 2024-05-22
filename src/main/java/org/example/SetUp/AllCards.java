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
    private  final Rank[] allRanks = Rank.values();
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

    public void dealCard(){
         dealtCard = deckOfCards.stream()
                .findFirst()
                 .orElse(null);
        assert dealtCard != null;
        String uniCode = "";
        if (dealtCard.getSuit().equals(Suit.HEARTS)) {
            uniCode = "♥";
        }
        if (dealtCard.getSuit().equals(Suit.DIAMONDS)) {
            uniCode = "♦";
        }
        if (dealtCard.getSuit().equals(Suit.CLUBS)) {
            uniCode = "♣";
        }
        if (dealtCard.getSuit().equals(Suit.SPADES)) {
            uniCode = "♠";
        }
        if(dealtCard.getScore() < 10) {
            System.out.println(" \n"
                    + " _____________\n"
                    + "| " + dealtCard.getScore() + "         " + dealtCard.getScore() + " |\n"
                    + "|             |\n"
                    + "|             |\n"
                    + "|      " + uniCode + "      |\n"
                    + "|             |\n"
                    + "| " + dealtCard.getScore() + "         " + dealtCard.getScore() + " |\n"
                    + "|_____________|\n");
            System.out.println("Dealt Card: " + dealtCard);
        } else {
            System.out.println(" \n"
                    + " _____________\n"
                    + "| " + dealtCard.getScore() + "       " + dealtCard.getScore() + " |\n"
                    + "|             |\n"
                    + "|             |\n"
                    + "|      " + uniCode + "      |\n"
                    + "|             |\n"
                    + "| " + dealtCard.getScore() + "       " + dealtCard.getScore() + " |\n"
                    + "|_____________|\n");
            System.out.println("Dealt Card: " + dealtCard);
        }
    }

    public void resetDeck() {
        deckOfCards = allCards;
    }

    public void printCards() {
        System.out.println(deckOfCards);
    }
}
