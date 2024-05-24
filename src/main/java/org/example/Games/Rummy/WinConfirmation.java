package org.example.Games.Rummy;

import org.example.SetUp.Cards;
import org.example.SetUp.Rank;
import org.example.SetUp.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class WinConfirmation {
    static Set<Suit> suitsCount = new HashSet<>();
    static Set<Rank> ranksCount = new HashSet<>();

    public static void checkingNumberOfSuits(List<Cards> playersFinalHand, String player, String nextPlayer) {
        for (Cards card : playersFinalHand) {
            suitsCount.add(card.getSuit());
        }

        System.out.println("number of suits are: " + suitsCount.size());

        if(suitsCount.size() == 1) {
            checkStraights(playersFinalHand, player, nextPlayer);
        } else if (suitsCount.size() == 2) {
            checkingNumberOfRanks(playersFinalHand);
        } else if (suitsCount.size() > 2) {
//            System.out.println("Sorry " + player + " you didn't win." +"\n");
//            PlayerInteraction.nextPlayer(player, nextPlayer);
            checkingNumberOfRanks(playersFinalHand);
        }
    }

    public static void checkStraights(List<Cards> playersFinalHand, String player, String nextPlayer) {
         Optional<Cards> smallest = playersFinalHand.stream().min((a, b) -> a.getScore() - b.getScore());
         Optional<Cards> biggest = playersFinalHand.stream().max((a, b) -> a.getScore() - b.getScore());

         if (biggest.get().getScore() - smallest.get().getScore() == 6) {
             playerWon(player);
         } else {
             System.out.println("Sorry " + player + " you didn't win." +"\n");
             PlayerInteraction.nextPlayer(player, nextPlayer);
         }
    }

    public static void playerWon(String player) {
        System.out.println("Congratulations you won!");
    }

    public static void checkingNumberOfRanks(List<Cards> playersFinalHand) {
//        for (Cards card : playersFinalHand) {
//            ranksCount.add(card.getRank());
//        }
//
//        System.out.println("number of ranks are: " + ranksCount.size());
//
//        System.out.println(ranksCount);
        System.out.println(suitsCount.stream().findFirst());
        System.out.println(suitsCount.stream().findFirst());

    }

}
