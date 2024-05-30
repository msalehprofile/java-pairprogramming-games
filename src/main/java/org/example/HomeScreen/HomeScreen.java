package org.example.HomeScreen;

import org.example.Games.Poker.Poker;
import org.example.Games.Rummy.Rummy;

import java.util.Objects;
import java.util.Scanner;

public class HomeScreen {
    static String chosenGame;
    public static void openHome() {
        System.out.println("\nWELCOME TO THE GAMES \n");
        gameChoices();
    }

    public static void gameChoices() {
        System.out.println("Please chose the game you would like to play: "
                +"\n1: Rummy"
                +"\n2: Poker" +
                "\n3: I don't want to play either");
        Scanner openGames = new Scanner(System.in);
        chosenGame = openGames.nextLine();
        if(!chosenGame.matches("\\d") || Integer.parseInt(chosenGame) > 3 || Integer.parseInt(chosenGame) < 1) {
            System.out.println("\nPlease chose a valid option.\n");
            gameChoices();
        } if (Objects.equals(chosenGame, "1")) {
            Rummy.playRummy();
        } else if (Objects.equals(chosenGame, "2")) {
            Poker.playPoker();
        } else if (Objects.equals(chosenGame, "3")){
            System.out.println("\nSee you soon, goodbye!");
        }

    }
}
