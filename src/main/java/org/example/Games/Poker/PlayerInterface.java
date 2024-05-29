package org.example.Games.Poker;

import java.util.Scanner;

import static org.example.Games.Poker.PokerLogic.getNumberOfPlayers;
import static org.example.Games.Poker.PokerLogic.numberOfPlayers;

public class PlayerInterface {
    static int numberOfPlayers = getNumberOfPlayers();
    static int activePlayers = numberOfPlayers;
    static int playerRoundSelection;
    static int playerChips;
    static int roundChips = 0;
    protected static void roundInteraction(int numberOfPlayers){
        for (int i = 0; i < activePlayers; i++) {
            Scanner playerSelection = new Scanner(System.in);
            System.out.println("Player " + (i + 1) + " please select what you would like to do");
            System.out.println("1: Fold");
            System.out.println("2: Call");
            System.out.println("3: Raise");
            playerRoundSelection = playerSelection.nextInt();
            if (playerRoundSelection == 1) {
                System.out.println("Player " + (i + 1) + " has folded");
                activePlayers--;
            }
            else if (playerRoundSelection == 2){
                System.out.println("Player" + (i + 1) + "has called");
            }
            else if (playerRoundSelection == 3){
                System.out.println("How much would you like to raise by?");
                int raiseAmount = playerSelection.nextInt();
                if (raiseAmount > playerChips) {
                    System.out.println("You do not have enough chips to raise by " + raiseAmount);
                }
                else if (raiseAmount < playerChips) {
                    System.out.println("Player " + (i + 1) + " has raised by " + raiseAmount);
                    playerChips = playerChips - raiseAmount;
                    roundChips = roundChips + raiseAmount;

                }
            }
        }
    }
}
