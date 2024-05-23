package org.example.Games.Rummy;

import org.example.SetUp.UserCreation;

public class Rummy {
    private static final String title = "Rummy";
    private static final String rules = "The aim of the game is to put your cards into two types of combinations:\n" +
            "1: Runs: Consecutive sequences of three or more cards of the same suit\n" +
            "\n" +
            "2: Sets: 3 or 4 cards of the same rank.\n"
            + "\n"
            + "Each player starts with 7 random cards\n"
            + "Taking in turns you will pick up a card either from the discarded card pile which is visible to all, or the untouched pile for a surprise card. \n"
            + "You then have to discard one of your cards\n"
            + "Plays must only ever have seven cards.\n"
            + "The game ends when someone announces 'Rummy'.";


    public static void playRummy() {
        RummySetUp rummy = new RummySetUp(title,rules);
        rummy.printName();
        rummy.printRules();

        rummy.play();
    }

}
