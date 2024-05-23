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

    private static final List<Cards> originalDeck = new ArrayList<>();
    private static List<Cards> deckOfCards = originalDeck;
    public static Cards dealtCard;

    public RummySetUp(String title, String rules) {
        super(title, rules);
    }

    static List<Cards> playerOneCards = new ArrayList<>();
    List<Cards> playerTwoCards = new ArrayList<>();
    List<Cards> playerThreeCards = new ArrayList<>();
    List<Cards> playerFourCards = new ArrayList<>();

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

        System.out.println("\n" + "We are now going to deal out the cards, " + playerOne + " please take a look at the screen first.\n"
                + "\n" + "Select 1 to continue: \n" +
                "1: Continue");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextInt();

        if (confirmation == 1) {

            switch (numberOfPlayers) {
                case 2:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerOneCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerTwoCards.add(allCards.getDealtCard());
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
                        playerOneCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerTwoCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);

                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerThree);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerThreeCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerThree + " your cards are: " + playerThreeCards);
                    PlayerInteraction.seenCardConfirmaion(playerThree);
                    break;
                case 4:
                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerOneCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerOne + " your cards are: " + playerOneCards);

                    PlayerInteraction.seenCardConfirmaion(playerOne);
                    PlayerInteraction.nextPlayer(playerOne, playerTwo);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerTwoCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n " + playerTwo + " your cards are: " + playerTwoCards);

                    PlayerInteraction.seenCardConfirmaion(playerTwo);
                    PlayerInteraction.nextPlayer(playerTwo, playerThree);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerThreeCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerThree + " your cards are: " + playerThreeCards);

                    PlayerInteraction.seenCardConfirmaion(playerThree);
                    PlayerInteraction.nextPlayer(playerThree, playerFour);

                    for (int i = 0; i < 7; i++) {
                        allCards.dealCard();
                        dealtCard = allCards.getDealtCard();
                        playerFourCards.add(allCards.getDealtCard());
                        allCards.getCardVisual(dealtCard);
                    }
                    System.out.println("\n" + playerFour + " your cards are: " + playerFourCards);
                    PlayerInteraction.seenCardConfirmaion(playerFour);

                    break;
            }

            while (true) {
                switch (numberOfPlayers) {
                    case 2:
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
                        PlayerInteraction.gameState(playerOne);
                        Scanner gameState = new Scanner(System.in);
                        confirmation = gameState.nextInt();
                        if (confirmation != 1) {
                            break;
                        }
                        PlayerInteraction.nextPlayer(playerOne, playerTwo);
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
                            if (confirmation == 1) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
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
                            PlayerInteraction.nextPlayer(playerTwo, playerOne);
                        }

                        break;
                    case 3:
                        break;
                }
            }
        }
    }

    @Override
    public void restart() {

    }
}
