package org.example.Games.Poker;

import org.example.SetUp.AllCards;
import org.example.SetUp.Cards;

import java.util.List;
import java.util.Scanner;

import static org.example.Games.Poker.PokerLogic.*;

public class PlayerInterface {
    static int numberOfPlayers = getNumberOfPlayers();
    static int playerRoundSelection;
    static int currentPlayerChips;
    static int buyInAmount = 25;
    static int roundChips = 0;
    static int lastBet = 0;
    static boolean secondRound = false;
    static boolean correctInput = false;
    static int consecutiveCallsOrChecks = 0;
    static boolean raiseOccurred = false;

    protected static void buyIn() {
        Scanner buyInSelection = new Scanner(System.in); // Initialize Scanner once
        for (int i = 0; i < numberOfPlayers; i++) {
            isPlayerActive.set(i, false);
            currentPlayerChips = getPlayerChips().get(i);
            System.out.println("Player " + playerNames.get(i) + ", you have " + currentPlayerChips + " chips. Would you like to buy in for 25 chips?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            int buyInChoice = buyInSelection.nextInt();
            if (buyInChoice == 1) {
                currentPlayerChips -= buyInAmount;
                playerChips.set(i, currentPlayerChips);
                roundChips += buyInAmount;
                System.out.println("Player " + (playerNames.get(i)) + " has bought in for 25 chips");
                isPlayerActive.set(i, true);
            } else if (buyInChoice == 2) {
                System.out.println("Player " + (playerNames.get(i)) + " has not bought in for 25 chips");
            } else {
                System.out.println("Invalid input. Please try again.");
                i--; // Repeat the iteration for the same player
            }
        }
    }

    protected static void roundInteraction(int numberOfPlayers) {
        Scanner playerSelection = new Scanner(System.in); // Initialize Scanner once
        consecutiveCallsOrChecks = 0;
        raiseOccurred = false;

        while (consecutiveCallsOrChecks < numberOfPlayers) {
            for (int i = 0; i < numberOfPlayers; i++) {
                if (!isPlayerActive.get(i)) {
                    continue; // Skip inactive players
                }

                correctInput = false; // Reset correctInput for each player

                while (!correctInput) {
                    currentPlayerChips = getPlayerChips().get(i);
                    System.out.println("Player " + playerNames.get(i) + ", your cards are:");
                    displayPlayerHand(getPlayerHands().get(i));
                    System.out.println("You have " + currentPlayerChips + " chips. Please select what you would like to do:");
                    System.out.println("1: Fold");
                    System.out.println("2: Call");
                    System.out.println(raiseOccurred ? "" : "3: Raise");
                    System.out.println("4: Check");

                    playerRoundSelection = playerSelection.nextInt();

                    if (playerRoundSelection == 1) {
                        System.out.println("Player " + playerNames.get(i) + " has folded");
                        correctInput = true;
                        isPlayerActive.set(i, false);
                        consecutiveCallsOrChecks = 0; // Reset on fold
                    } else if (playerRoundSelection == 2) {
                        if (raiseOccurred && currentPlayerChips >= lastBet) {
                            System.out.println("Player " + playerNames.get(i) + " has called");
                            currentPlayerChips -= lastBet;
                            playerChips.set(i, currentPlayerChips);
                            roundChips += lastBet;
                            correctInput = true;
                            consecutiveCallsOrChecks++;
                        } else if (!raiseOccurred) {
                            System.out.println("No raises to call. You can check.");
                        } else {
                            System.out.println("You do not have enough chips to call. Please select another option.");
                        }
                    } else if (playerRoundSelection == 3 && !raiseOccurred) {
                        System.out.println("How much would you like to raise by?");
                        int raiseAmount = playerSelection.nextInt();

                        if (raiseAmount > currentPlayerChips) {
                            System.out.println("You do not have enough chips to raise by " + raiseAmount);
                        } else if (raiseAmount > 0) {
                            System.out.println("Player " + playerNames.get(i) + " has raised by " + raiseAmount);
                            currentPlayerChips -= raiseAmount;
                            playerChips.set(i, currentPlayerChips);
                            roundChips += raiseAmount;
                            lastBet = raiseAmount;
                            correctInput = true;
                            consecutiveCallsOrChecks = 0; // Reset on raise
                            raiseOccurred = true;
                        } else {
                            System.out.println("Raise amount must be greater than zero.");
                        }
                    } else if (playerRoundSelection == 4) {
                        if (!raiseOccurred) {
                            System.out.println("Player " + playerNames.get(i) + " has checked");
                            correctInput = true;
                            consecutiveCallsOrChecks++;
                        } else {
                            System.out.println("You cannot check after a raise. Please call or raise.");
                        }
                    } else {
                        System.out.println("Please enter a valid input.");
                    }
                }
            }
        }
        System.out.println("Round has ended.");
    }

    static void displayPlayerHand(List<Cards> playerHand) {
        for (Cards card : playerHand) {
            deck.getCardVisual(card);
            System.out.print(" ");
        }
        System.out.println();
    }
}
