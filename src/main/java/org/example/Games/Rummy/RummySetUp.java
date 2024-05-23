package org.example.Games.Rummy;

import org.example.SetUp.AllCards;
import org.example.SetUp.Cards;
import org.example.SetUp.Game;
import org.example.SetUp.UserCreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RummySetUp extends Game {
    private static int numberOfPlayers;
    private static String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    private static int confirmation;

    public RummySetUp(String title, String rules) {
        super(title, rules);
    }

    List<Cards> playerOneCards = new ArrayList<>();
    List<Cards> playerTwoCards = new ArrayList<>();
    List<Cards> playerThreeCards = new ArrayList<>();
    List<Cards> playerFourCards = new ArrayList<>();


    @Override
    public void play() {
        UserCreation userCreation = new UserCreation();
        userCreation.creatingUsers();
        numberOfPlayers = userCreation.getNumberOfPlayers();

        if(numberOfPlayers == 1) {
            System.out.println("\n"
                    + "You can't play Rummy alone, please select again.");
            userCreation.creatingUsers();
        }
        System.out.println("\n" +"You have selected to play with " + numberOfPlayers + " people.");
        userCreation.assigningNames();
        playerOne = userCreation.getPlayerOne();
        playerTwo = userCreation.getPlayerTwo();
        playerThree = userCreation.getPlayerThree();
        playerFour = userCreation.getPlayerFour();
        dealCards();
//        allCards.printCards();
    }

    public void dealCards() {
        AllCards allCards = new AllCards();
        allCards.createAllCards();
        allCards.shuffle();

        System.out.println("\n" + "We are now going to deal out the cards, " + playerOne + " please take a look at the screen first.\n"
                + "\n" +"Select 1 to continue: \n" +
                "1: Continue");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextInt();

        if (confirmation == 1) {

            switch (numberOfPlayers) {
                case 2:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerOneCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerTwoCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);
                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    break;
                case 3:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerOneCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerTwoCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);

                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerThree);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerThreeCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n" + playerThree + " your cards are: " + playerThreeCards);
                    PlayerInteraction.seenCardConfirmaion(playerThree);
                    break;
                case 4:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerOneCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerTwoCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);

                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerThree);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerThreeCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n" + playerThree + " your cards are: " + playerThreeCards);

                    PlayerInteraction.seenCardConfirmaion(playerThree);
                    PlayerInteraction.nextPlayer(playerThree, playerFour);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        playerFourCards.add(allCards.getDealtCard());
                        allCards.getCardVisual();
                    }
                    System.out.println("\n" + playerFour + " your cards are: " + playerFourCards);
                    PlayerInteraction.seenCardConfirmaion(playerFour);
                    System.out.println("\n" + "Please pass the screen to " + playerFour);
                    break;
            }
        }

    }

    @Override
    public void restart() {

    }
}
