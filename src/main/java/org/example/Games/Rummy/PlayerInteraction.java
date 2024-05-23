package org.example.Games.Rummy;

import java.util.Scanner;

public class PlayerInteraction {
    private static int confirmation;


    public static void nextPlayer(String playersName, String nextPlayer) {
        System.out.println("Now please can you pass the screen to " + nextPlayer + "\n" + "\n"
                + nextPlayer + " please confirm you now have the screen:\n"
                + "1: Confirmed.");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextInt();
        if(confirmation == 1 ){
            System.out.println("\n" + playersName + " thank you for confirming.");
        } else {
            nextPlayer(playersName, nextPlayer);
        }
    }

    public static void seenCardConfirmaion(String playersName) {
        System.out.println("\n" + playersName + " please confirm you have seen your cards: \n"
                + "1: Confirmed.");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextInt();
        if(confirmation == 1 ){
            System.out.println("\n" + "Thank you for confirming "+ playersName  );
        } else {
            seenCardConfirmaion(playersName);
        }
    }
}
