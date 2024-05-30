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
                if (activePlayers == 1 && lastActivePlayerIndex != -1) {
                    int remainingPlayerChips = PokerLogic.getPlayerChips().get(lastActivePlayerIndex) + roundChips;
                    PokerLogic.getPlayerChips().set(lastActivePlayerIndex, remainingPlayerChips);
                    System.out.println("All other players have folded. " + PokerLogic.playerNames.get(lastActivePlayerIndex) +
                            " wins the round and receives " + roundChips + " chips.");
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
                                        int remainingPlayerChips = PokerLogic.getPlayerChips().get(j) + roundChips;
                                        PokerLogic.getPlayerChips().set(j, remainingPlayerChips);
                                        System.out.println("All other players have folded. " + PokerLogic.playerNames.get(j) +
                                                " wins the round and receives " + roundChips + " chips.");
                                        roundChips = 0; // Reset round chips after awarding to the winner
                                        return; // End the round
                                    }
                                }
                            }
                        } else if (playerRoundSelection == 2) {
                            // Call
                            if (currentPlayerChips >= lastBet) {
                                currentPlayerChips -= lastBet;
                                PokerLogic.playerChips.set(i, currentPlayerChips);
                                roundChips += lastBet;
                                System.out.println("Player " + PokerLogic.playerNames.get(i) + " calls and now has " + currentPlayerChips + " chips.");
                                correctInput = true;
                                consecutiveCallsOrChecks++;
                            } else {
                                System.out.println("Not enough chips to call. Please choose another option.");
                            }
                        } else if (playerRoundSelection == 3) {
                            // Raise
                            System.out.println("Enter raise amount (must be greater than " + lastBet + "):");
                            int raiseAmount = playerSelection.nextInt();
                            if (raiseAmount > lastBet && currentPlayerChips >= raiseAmount) {
                                currentPlayerChips -= raiseAmount;
                                PokerLogic.playerChips.set(i, currentPlayerChips);
                                roundChips += raiseAmount;
                                lastBet = raiseAmount;
                                raiseOccurred = true;
                                consecutiveCallsOrChecks = 0; // Reset consecutiveCallsOrChecks because a raise occurred
                                System.out.println("Player " + PokerLogic.playerNames.get(i) + " raises by " + raiseAmount + " and now has " + currentPlayerChips + " chips.");
                                correctInput = true;
                            } else {
                                System.out.println("Invalid raise amount. Please enter an amount greater than " + lastBet + " and ensure you have enough chips.");
                            }
                        } else if (playerRoundSelection == 4) {
                            // Check
                            if (lastBet == 0) {
                                System.out.println("Player " + PokerLogic.playerNames.get(i) + " checks.");
                                correctInput = true;
                                consecutiveCallsOrChecks++;
                            } else {
                                System.out.println("Cannot check. A bet has been made. Please choose another option.");
                            }
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }

                        // If all active players have called or checked consecutively, end the round
                        if (consecutiveCallsOrChecks >= activePlayers && !raiseOccurred) {
                            System.out.println("Round has ended.");
                            return;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                        playerSelection.next(); // Consume the invalid input
                    }
                }
            }

            // Reset raiseOccurred at the end of the round to allow raising in the next round
            if (consecutiveCallsOrChecks >= activePlayers) {
                System.out.println("Round has ended.");
                raiseOccurred = false;
                lastBet = 0;
                break;
            }
        }
    }

    static void displayPlayerHand(List<Cards> playerHand) {
        for (Cards card : playerHand) {
            PokerLogic.deck.getCardVisual(card);
            System.out.print(" ");
        }
        System.out.println();
    }}