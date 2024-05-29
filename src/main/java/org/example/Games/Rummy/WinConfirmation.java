package org.example.Games.Rummy;

import org.example.HomeScreen.HomeScreen;
import org.example.SetUp.Cards;
import org.example.SetUp.Rank;
import org.example.SetUp.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class WinConfirmation {
    static List<Suit> suitsCount = new ArrayList<>();
    static List<Rank> rankCount = new ArrayList<>();
    static boolean gameFinished= false;

    public static boolean isGameFinished() {
        return gameFinished;
    }

    public static void checkingNumberOfSuits(List<Cards> playersFinalHand, String player, String nextPlayer) {
        for (Cards card : playersFinalHand) {
            suitsCount.add(card.getSuit());
        }

        List<Suit> uniqueSuits = suitsCount.stream().distinct().collect(Collectors.toList());

        if(uniqueSuits.size() == 1) {
            checkStraights(playersFinalHand, player, nextPlayer);
        } else if (uniqueSuits.size() == 2) {
            checkingSuitsBasedWin(playersFinalHand, player, nextPlayer);
        } else if (uniqueSuits.size() > 2) {
            checkingRanksBasedWin(playersFinalHand, player, nextPlayer);
        }
    }

    public static void checkStraights(List<Cards> playersFinalHand, String player, String nextPlayer) {
         Optional<Cards> smallest = playersFinalHand.stream().min((a, b) -> a.getScore() - b.getScore());
         Optional<Cards> biggest = playersFinalHand.stream().max((a, b) -> a.getScore() - b.getScore());

         if (biggest.get().getScore() - smallest.get().getScore() == 6) {
             playerWon(player, playersFinalHand);
         } else {
             System.out.println("\n" + "Sorry " + player + " you didn't win.\n");
             PlayerInteraction.nextPlayer(player, nextPlayer);
         }
    }

    public static void checkingRanksBasedWin(List<Cards> playersFinalHand, String player, String nextPlayer) {
        for (Cards card : playersFinalHand) {
            rankCount.add(card.getRank());
        }

        List<Rank> uniqueRanks = rankCount.stream().distinct().collect(Collectors.toList());

        if (uniqueRanks.size() > 2) {
            System.out.println("Sorry " + player + " you didn't win." +"\n");
            PlayerInteraction.nextPlayer(player, nextPlayer);
        } else if (uniqueRanks.size() == 2) {
            Rank firstRank = uniqueRanks.get(0);
            Rank secondRank = uniqueRanks.get(1);

            List<Cards> listOfFirstSuit = playersFinalHand.stream().filter(cards -> cards.getRank() == firstRank).distinct().collect(Collectors.toList());
            List<Cards> listOfSecondSuit = playersFinalHand.stream().filter(cards -> cards.getRank() == secondRank).distinct().collect(Collectors.toList());

            if( listOfFirstSuit.size() == 3 && listOfSecondSuit.size() == 4) {
                playerWon(player, playersFinalHand);
            } else if (listOfFirstSuit.size() == 4 && listOfSecondSuit.size() == 3) {
                playerWon(player, playersFinalHand);
            } else {
                System.out.println("Sorry " + player + " you didn't win." +"\n");
                PlayerInteraction.nextPlayer(player, nextPlayer);
            }
        }
    }

    public static void playerWon(String player, List<Cards> playersFinalHand) {
        System.out.println("\nCongratulations" + player + " you won! Your winning hand is:\n"
                        + "\n" + playersFinalHand + "\n"
                        + "\n"
                        + "\nPlease decide whether you would like to play again: "
                        + "\n1: Play again!"
                        + "\n2: Exit game!");
        gameFinished = true;
        Scanner playerInput = new Scanner(System.in);
        int playAgainDecider = playerInput.nextInt();
        if(playAgainDecider > 2 || playAgainDecider < 1) {
            System.out.println("\nPlease chose a valid option.");
            System.out.println("\nPlease decide whether you would like to play again: "
                    + "\n1: Play again!"
                    + "\n2: Exit game!");
            Scanner playerInputRetry = new Scanner(System.in);
            playAgainDecider = playerInputRetry.nextInt();
        }
        if (playAgainDecider == 1) {
            Rummy.playRummy();
        } else if (playAgainDecider ==2) {
            HomeScreen.openHome();
        }

    }

    public static void checkingSuitsBasedWin(List<Cards> playersFinalHand, String player, String nextPlayer) {
        List<Suit> uniqueSuits = suitsCount.stream().distinct().collect(Collectors.toList());

        Suit firstSuit = uniqueSuits.get(0);
        Suit secondSuit = uniqueSuits.get(1);

        List<Cards> listOfFirstSuit = playersFinalHand.stream().filter(cards -> cards.getSuit() == firstSuit).collect(Collectors.toList());
        List<Cards> listOfSecondSuit = playersFinalHand.stream().filter(cards -> cards.getSuit() == secondSuit).collect(Collectors.toList());

        if (listOfFirstSuit.size() == 3) {
            Optional<Cards> firstSmallest = listOfFirstSuit.stream().min((a, b) -> a.getScore() - b.getScore());
            Optional<Cards> firstBiggest = listOfFirstSuit.stream().max((a, b) -> a.getScore() - b.getScore());

            if (firstBiggest.get().getScore() - firstSmallest.get().getScore() == 2) {
                Optional<Cards> secondSmallest = listOfSecondSuit.stream().min((a, b) -> a.getScore() - b.getScore());
                Optional<Cards> secondBiggest = listOfSecondSuit.stream().max((a, b) -> a.getScore() - b.getScore());
                if (secondBiggest.get().getScore() - secondSmallest.get().getScore() == 3) {
                    playerWon(player, playersFinalHand);
                } else {
                    System.out.println("Sorry " + player + " you didn't win." +"\n");
                    PlayerInteraction.nextPlayer(player, nextPlayer);
                }
            } else {
                System.out.println("Sorry " + player + " you didn't win." +"\n");
                PlayerInteraction.nextPlayer(player, nextPlayer);
            }

        } else if (listOfFirstSuit.size() == 4) {
            Optional<Cards> firstSmallest = listOfFirstSuit.stream().min((a, b) -> a.getScore() - b.getScore());
            Optional<Cards> firstBiggest = listOfFirstSuit.stream().max((a, b) -> a.getScore() - b.getScore());

            if (firstBiggest.get().getScore() - firstSmallest.get().getScore() == 3) {
                Optional<Cards> secondSmallest = listOfSecondSuit.stream().min((a, b) -> a.getScore() - b.getScore());
                Optional<Cards> secondBiggest = listOfSecondSuit.stream().max((a, b) -> a.getScore() - b.getScore());
                if (secondBiggest.get().getScore() - secondSmallest.get().getScore() == 2) {
                    playerWon(player, playersFinalHand);
                } else {
                    System.out.println("Sorry " + player + " you didn't win." +"\n");
                    PlayerInteraction.nextPlayer(player, nextPlayer);
                }
            } else {
                System.out.println("Sorry " + player + " you didn't win." +"\n");
                PlayerInteraction.nextPlayer(player, nextPlayer);
            }
        }
    }
}
