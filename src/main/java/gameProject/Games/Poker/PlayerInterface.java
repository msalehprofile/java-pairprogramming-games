package gameProject.Games.Poker;

import gameProject.SetUp.Cards;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerInterface {
    static int numberOfPlayers = PokerLogic.getNumberOfPlayers();
    static int playerRoundSelection;
    static int currentPlayerChips;
    static int buyInAmount = 25;
    static int roundChips = 0;
    static int lastBet = 0;
    static boolean secondRound = false;
    static boolean correctInput = false;
    static int consecutiveCallsOrChecks = 0;
    static boolean raiseOccurred = false;
    public static boolean playerWonRound = false;

    protected static void buyIn() {
        Scanner buyInSelection = new Scanner(System.in);
        for (int i = 0; i < numberOfPlayers; i++) {
            PokerLogic.isPlayerActive.set(i, false);
            currentPlayerChips = PokerLogic.getPlayerChips().get(i);
            System.out.println("Player " + PokerLogic.playerNames.get(i) + ", you have " + currentPlayerChips + " chips. Would you like to buy in for 25 chips?");
            System.out.println("1: Yes");
            System.out.println("2: No");

            boolean validInput = false;
            while (!validInput) {
                try {
                    int buyInChoice = buyInSelection.nextInt();
                    if (buyInChoice == 1) {
                        currentPlayerChips -= buyInAmount;
                        PokerLogic.playerChips.set(i, currentPlayerChips);
                        roundChips += buyInAmount;
                        System.out.println("Player " + PokerLogic.playerNames.get(i) + " has bought in for 25 chips");
                        PokerLogic.isPlayerActive.set(i, true);
                        validInput = true;
                    } else if (buyInChoice == 2) {
                        System.out.println("Player " + PokerLogic.playerNames.get(i) + " has not bought in for 25 chips");
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter 1 for Yes or 2 for No.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter 1 for Yes or 2 for No.");
                    buyInSelection.next(); // Consume the invalid input
                }
            }
        }
    }

    protected static void roundInteraction(int numberOfPlayers) {
        Scanner playerSelection = new Scanner(System.in);
        consecutiveCallsOrChecks = 0;
        raiseOccurred = false;
        lastBet = 0; // Reset lastBet for each round
        playerWonRound = false; // Reset playerWonRound for each round
        int raiserIndex = -1;

        while (true) {
            int activePlayers = 0;
            int lastActivePlayerIndex = -1;

            // Count active players and track the index of the last active player
            for (int i = 0; i < PokerLogic.isPlayerActive.size(); i++) {
                if (PokerLogic.isPlayerActive.get(i)) {
                    activePlayers++;
                    lastActivePlayerIndex = i;
                }
            }

            if (activePlayers <= 1) {
                // If there is only one active player, award them the round chips
                if (activePlayers == 1) {
                    if (!playerWonRound) { // Check if the player hasn't already won
                        int remainingPlayerChips = PokerLogic.getPlayerChips().get(lastActivePlayerIndex) + roundChips;
                        PokerLogic.getPlayerChips().set(lastActivePlayerIndex, remainingPlayerChips);
                        System.out.println("All other players have folded. " + PokerLogic.playerNames.get(lastActivePlayerIndex) +
                                " wins the round and now has " + remainingPlayerChips + " chips.");
                        playerWonRound = true; // Set the flag to true
                    }
                    roundChips = 0; // Reset round chips after awarding to the winner
                }
                break; // Exit the loop if only one or no player is left active
            }

            // Process the round for active players
            for (int i = 0; i < numberOfPlayers; i++) {
                if (!PokerLogic.isPlayerActive.get(i)) {
                    continue; // Skip inactive players
                }

                correctInput = false; // Reset correctInput for each player

                while (!correctInput) {

                    if(!PokerLogic.getGameCards().isEmpty()) {
                        displayPlayerHand(PokerLogic.getGameCards());
                    }
                    currentPlayerChips = PokerLogic.getPlayerChips().get(i);
                    System.out.println("Player " + PokerLogic.playerNames.get(i) + ", your cards are:");
                    displayPlayerHand(PokerLogic.getPlayerHands().get(i));
                    System.out.println("You have " + currentPlayerChips + " chips. Please select what you would like to do:");
                    System.out.println("1: Fold");
                    System.out.println("2: Call (" + lastBet + " chips)");
                    System.out.println("3: Raise");
                    System.out.println("4: Check");

                    try {
                        playerRoundSelection = playerSelection.nextInt();

                        if (playerRoundSelection == 1) {
                            // Fold
                            System.out.println("Player " + PokerLogic.playerNames.get(i) + " has folded");
                            correctInput = true;
                            PokerLogic.isPlayerActive.set(i, false);
                            activePlayers--;

                            if (activePlayers == 1) {
                                for (int j = 0; j < numberOfPlayers; j++) {
                                    if (PokerLogic.isPlayerActive.get(j)) {
                                        if (!playerWonRound) { // Check if the player hasn't already won
                                            int remainingPlayerChips = PokerLogic.getPlayerChips().get(j) + roundChips;
                                            PokerLogic.getPlayerChips().set(j, remainingPlayerChips);
                                            System.out.println("All other players have folded. " + PokerLogic.playerNames.get(j) +
                                                    " wins the round and now has " + remainingPlayerChips + " chips.");
                                            playerWonRound = true; // Set the flag to true
                                        }
                                        roundChips = 0; // Reset round chips after awarding to the winner
                                        return; // End the round
                                    }
                                }
                            }
                        } else if (playerRoundSelection == 2) {
                            // Call
                            if (currentPlayerChips >= lastBet) {
                                PokerLogic.playerChips.set(i, currentPlayerChips - lastBet);
                                roundChips += lastBet;
                                System.out.println("Player " + PokerLogic.playerNames.get(i) + " has called with " + lastBet + " chips");
                                correctInput = true;
                                consecutiveCallsOrChecks++;
                            } else {
                                System.out.println("You don't have enough chips to call. Please choose another action.");
                            }
                        } else if (playerRoundSelection == 3) {
                            // Raise
                            System.out.println("Enter the amount you want to raise:");
                            int raiseAmount = playerSelection.nextInt();
                            if (raiseAmount > 0 && currentPlayerChips >= (lastBet + raiseAmount)) {
                                lastBet += raiseAmount;
                                PokerLogic.playerChips.set(i, currentPlayerChips - lastBet);
                                roundChips += lastBet;
                                System.out.println("Player " + PokerLogic.playerNames.get(i) + " has raised by " + raiseAmount + " chips");
                                correctInput = true;
                                consecutiveCallsOrChecks = 0; // Reset consecutive calls or checks
                                raiseOccurred = true;
                                raiserIndex = i; // Track the raiser index
                            } else {
                                System.out.println("You don't have enough chips to raise. Please choose another action.");
                            }
                        } else if (playerRoundSelection == 4) {
                            // Check
                            if (lastBet == 0) {
                                System.out.println("Player " + PokerLogic.playerNames.get(i) + " has checked");
                                correctInput = true;
                                consecutiveCallsOrChecks++;
                            } else {
                                System.out.println("You cannot check as there is a bet to call. Please choose another action.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                        }

                        // Check if all players except the raiser have called or all have checked
                        if ((consecutiveCallsOrChecks == activePlayers && !raiseOccurred) ||
                                (consecutiveCallsOrChecks == (activePlayers - 1) && raiseOccurred)) {
                            System.out.println("All players have either called or checked. Dealing the next card...");
                            return; // Exit the method to deal the next card
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                        playerSelection.next(); // Consume the invalid input
                    }
                }
            }
        }
    }


    static void displayPlayerHand(List<Cards> playerHand) {
        for (Cards card : playerHand) {
            PokerLogic.deck.getCardVisual(card);
            System.out.print(" ");
        }
        System.out.println();
    }
}
