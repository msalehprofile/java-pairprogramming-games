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
        Scanner playerSelection = new Scanner(System.in);
        consecutiveCallsOrChecks = 0;
        raiseOccurred = false;

        while (true) {
            int activePlayers = 0;
            int lastActivePlayerIndex = -1;

            // Count active players and track the index of the last active player
            for (int i = 0; i < isPlayerActive.size(); i++) {
                if (isPlayerActive.get(i)) {
                    activePlayers++;
                    lastActivePlayerIndex = i;
                }
            }

            if (activePlayers <= 1) {
                // If there is only one active player, award them the round chips
                if (activePlayers == 1 && lastActivePlayerIndex != -1) {
                    int remainingPlayerChips = getPlayerChips().get(lastActivePlayerIndex) + roundChips;
                    getPlayerChips().set(lastActivePlayerIndex, remainingPlayerChips);
                    System.out.println("All other players have folded. " + playerNames.get(lastActivePlayerIndex) +
                            " wins the round and receives " + roundChips + " chips.");
                    roundChips = 0; // Reset round chips after awarding to the winner
                }
                break; // Exit the loop if only one or no player is left active
            }

            // Process the round for active players
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
                    if (!raiseOccurred) {
                        System.out.println("3: Raise");
                    }
                    System.out.println("4: Check");

                    playerRoundSelection = playerSelection.nextInt();

                    if (playerRoundSelection == 1) {
                        System.out.println("Player " + playerNames.get(i) + " has folded");
                        correctInput = true;
                        isPlayerActive.set(i, false);
                        activePlayers--;

                        if (activePlayers == 1) {
                            for (int j = 0; j < numberOfPlayers; j++) {
                                if (isPlayerActive.get(j)) {
                                    int remainingPlayerChips = getPlayerChips().get(j) + roundChips;
                                    getPlayerChips().set(j, remainingPlayerChips);
                                    System.out.println("All other players have folded. " + playerNames.get(j) +
                                            " wins the round and receives " + roundChips + " chips.");
                                    return;// End the round
                                }
                                roundChips = 0;
                            }
                        }
                    }
                    // Other actions (Call, Raise, Check) remain the same
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
