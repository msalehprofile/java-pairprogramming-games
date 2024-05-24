package org.example.Games.Rummy;

import org.example.SetUp.AllCards;
import org.example.SetUp.Cards;
import org.example.SetUp.Game;
import org.example.SetUp.UserCreation;

import java.util.ArrayList;
import java.util.Collections;
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

    private static final List<Cards> originalDeck = new ArrayList<>();
    private static List<Cards> deckOfCards = originalDeck;
    public static Cards dealtCard;

    public RummySetUp(String title, String rules) {
        super(title, rules);
    }

    static List<Cards> playerOneCards = new ArrayList<>();
    static List<Cards> playerTwoCards = new ArrayList<>();
    static List<Cards> playerThreeCards = new ArrayList<>();
    static List<Cards> playerFourCards = new ArrayList<>();

    static List<Cards> discardedPile = new ArrayList<>();


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

        if(numberOfPlayers > 4 | numberOfPlayers < 1) {
            System.out.println("\n"
                    + "Please select a valid option.");
            userCreation.creatingUsers();
        }

        userCreation.assigningNames();
        playerOne = userCreation.getPlayerOne();
        playerTwo = userCreation.getPlayerTwo();
        playerThree = userCreation.getPlayerThree();
        playerFour = userCreation.getPlayerFour();
        dealCards();
    }

    public void dealCards() {
        AllCards allCards = new AllCards();
        allCards.createAllCards();
        allCards.shuffle();
        deckOfCards = allCards.getDeckOfCards();

        PlayerInteraction.dealCards(playerOne);
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextInt();
        if (confirmation != 1) {
            System.out.println("\nPlease try again. ");
            PlayerInteraction.dealCards(playerOne);
            Scanner seenCardsTwo = new Scanner(System.in);
            confirmation = seenCardsTwo.nextInt();
        }

        if (confirmation == 1) {
            // dealing cards depending on number of players
            switch (numberOfPlayers) {
                case 2:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerOneCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerTwoCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);
                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerOne);
                    break;
                case 3:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerOneCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerTwoCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);

                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerThree);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerThreeCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerThree + " your cards are: " + playerThreeCards);
                    PlayerInteraction.seenCardConfirmaion(playerThree);
                    break;
                case 4:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerOneCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerTwoCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);

                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerThree);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerThreeCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerThree + " your cards are: " + playerThreeCards);

                    PlayerInteraction.seenCardConfirmaion(playerThree);
                    PlayerInteraction.nextPlayer(playerThree, playerFour);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerFourCards.add(dealtCard);
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerFour + " your cards are: " + playerFourCards);
                    PlayerInteraction.seenCardConfirmaion(playerFour);

                    break;
            }

            // main gameplay depending on number of players
            while (true) {
                switch (numberOfPlayers) {

                    case 2:
                        // player ones turn
                        System.out.println(playerOne + " your current cards are as followed: " + playerOneCards);
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
                        }
                        if (discardedPile.isEmpty()) {
                            PlayerInteraction.firstPlayerGameDecision(playerOne);
                            allCards.dealCard();
                            dealtCard = allCards.getDealtCard();
                            System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                            playerOneCards.add(dealtCard);
                            System.out.println("You currently have the below cards: ");
                            for (Cards card : playerOneCards) {
                                allCards.getCardVisual(card);
                            }
                            PlayerInteraction.playerRemoveCardChoice(playerOne, playerOneCards);
                            Scanner playerOneRemoves = new Scanner(System.in);
                            confirmation = playerOneRemoves.nextInt();
                            discardedPile.add(playerOneCards.get(confirmation - 1));

                            playerOneCards.remove(confirmation - 1);

                            System.out.println("\n" + playerOne + " your new set is as below: ");
                            for (Cards card : playerOneCards) {
                                allCards.getCardVisual(card);
                            }

                        } else {
                            PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextInt();
                            if(confirmation != 1 && confirmation !=2) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextInt();
                            }
                            if (confirmation == 1) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);
                                System.out.println("You currently have the below cards: ");
                                for (Cards card : playerOneCards) {
                                    allCards.getCardVisual(card);
                                }
                                PlayerInteraction.playerRemoveCardChoice(playerOne, playerOneCards);
                                Scanner playerOneRemoves = new Scanner(System.in);
                                confirmation = playerOneRemoves.nextInt();
                                discardedPile.add(playerOneCards.get(confirmation - 1));

                                playerOneCards.remove(confirmation - 1);

                                System.out.println("\n" + playerOne + " your new set is as below: ");
                                for (Cards card : playerOneCards) {
                                    allCards.getCardVisual(card);
                                }
                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);
                                System.out.println("You currently have the below cards: ");
                                for (Cards card : playerOneCards) {
                                    allCards.getCardVisual(card);
                                }
                                PlayerInteraction.playerRemoveCardChoice(playerOne, playerOneCards);
                                Scanner playerOneRemoves = new Scanner(System.in);
                                confirmation = playerOneRemoves.nextInt();
                                discardedPile.add(playerOneCards.get(confirmation - 1));

                                playerOneCards.remove(confirmation - 1);

                                System.out.println("\n" + playerOne + " your new set is as below: ");
                                for (Cards card : playerOneCards) {
                                    allCards.getCardVisual(card);
                                }
                            }
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }

                        PlayerInteraction.gameState(playerOne, playerTwo, playerOneCards);

                        // player twos turn
                        System.out.println(playerTwo + " your current cards are as followed: " + playerTwoCards);
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }
                        if (discardedPile.isEmpty()) {
                            PlayerInteraction.firstPlayerGameDecision(playerTwo);
                            allCards.dealCard();
                            dealtCard = allCards.getDealtCard();
                            System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                            playerTwoCards.add(dealtCard);
                            System.out.println("You currently have the below cards: ");
                            for (Cards card : playerTwoCards) {
                                allCards.getCardVisual(card);
                            }
                            PlayerInteraction.playerRemoveCardChoice(playerTwo, playerTwoCards);
                            Scanner playerOneRemoves = new Scanner(System.in);
                            confirmation = playerOneRemoves.nextInt();
                            discardedPile.add(playerTwoCards.get(confirmation - 1));

                            playerTwoCards.remove(confirmation - 1);

                            System.out.println("\n" + playerTwo + " your new set is as below: ");
                            for (Cards card : playerTwoCards) {
                                allCards.getCardVisual(card);
                            }

                        } else {
                            PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextInt();
                            if(confirmation != 1 && confirmation !=2) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextInt();
                            }

                            if (confirmation == 1) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);
                                System.out.println("You currently have the below cards: ");
                                for (Cards card : playerTwoCards) {
                                    allCards.getCardVisual(card);
                                }
                                PlayerInteraction.playerRemoveCardChoice(playerTwo, playerTwoCards);
                                Scanner playerRemoves = new Scanner(System.in);
                                confirmation = playerRemoves.nextInt();
                                discardedPile.add(playerTwoCards.get(confirmation - 1));

                                playerTwoCards.remove(confirmation - 1);

                                System.out.println("\n" + playerTwo + " your new set is as below: ");
                                for (Cards card : playerTwoCards) {
                                    allCards.getCardVisual(card);
                                }
                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);
                                System.out.println("You currently have the below cards: ");
                                for (Cards card : playerTwoCards) {
                                    allCards.getCardVisual(card);
                                }
                                PlayerInteraction.playerRemoveCardChoice(playerTwo, playerTwoCards);
                                Scanner playerOneRemoves = new Scanner(System.in);
                                confirmation = playerOneRemoves.nextInt();
                                discardedPile.add(playerTwoCards.get(confirmation - 1));

                                playerTwoCards.remove(confirmation - 1);

                                System.out.println("\n" + playerTwo + " your new set is as below: ");
                                for (Cards card : playerTwoCards) {
                                    allCards.getCardVisual(card);
                                }
                            }


                            if (allCards.getDeckOfCards().isEmpty()) {
                                allCards.refreshStack(discardedPile);
                                discardedPile.clear();
                            }
                            PlayerInteraction.gameState(playerTwo, playerOne, playerTwoCards);
                        }



                        break;
                    case 3:
                        break;
                }
                // check hand
            }
        }
    }

    @Override
    public void restart() {

    }
}
