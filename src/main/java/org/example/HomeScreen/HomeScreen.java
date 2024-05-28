package org.example.HomeScreen;

import org.example.Games.Rummy.Rummy;

import java.util.Scanner;

public class HomeScreen {
    public static void openHome() {
        System.out.println("\nWELCOME TO THE GAMES \n");
        gameChoices();
        Scanner openGames = new Scanner(System.in);
        int chosenGame = openGames.nextInt();

        if(chosenGame > 3 || chosenGame < 1) {
            System.out.println("\nPlease chose a valid option.\n");
            gameChoices();
            Scanner openGamesRetry = new Scanner(System.in);
            chosenGame = openGamesRetry.nextInt();
        } if (chosenGame == 1) {
            Rummy.playRummy();
        } else if (chosenGame == 2) {
            System.out.println("Play Poker");
        } else{
            System.out.println("\nSee you soon, goodbye!");
        }
    }

    public static void gameChoices() {
        System.out.println("Please chose the game you would like to play: "
                +"\n1: Rummy"
                +"\n2: Poker" +
                "\n3: I don't want to play either");
    }
}
