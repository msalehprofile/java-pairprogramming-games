package org.example.Games.Rummy;

import org.example.SetUp.AllCards;
import org.example.SetUp.Cards;
import org.example.SetUp.Game;
import org.example.SetUp.UserCreation;

import java.util.*;


public class RummySetUp extends Game {
    private static String numberOfPlayers;
    private static String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    public static Cards dealtCard;
    public static String confirmation;

    public RummySetUp(String title, String rules) {
        super(title, rules);
    }

    static List<Cards> playerOneCards = new ArrayList<>();
    static List<Cards> playerTwoCards = new ArrayList<>();
    static List<Cards> playerThreeCards = new ArrayList<>();
    static List<Cards> playerFourCards = new ArrayList<>();
    static List<Cards> discardedPile = new ArrayList<>();

    static AllCards allCards = new AllCards();

    @Override
    public void play() {
        UserCreation userCreation = new UserCreation();
        userCreation.creatingUsers();

        if(Objects.equals(userCreation.getNumberOfPlayers(), "1")) {
            System.out.println("\n"
                    + "You can't play Rummy alone, please select again.");
            userCreation.creatingUsers();
        }

        if (!(userCreation.getNumberOfPlayers().equals("2") || userCreation.getNumberOfPlayers().equals("3") || userCreation.getNumberOfPlayers().equals("4"))) {
            System.out.println("\nPlease select a valid option.");
                userCreation.creatingUsers();
            }

        numberOfPlayers = userCreation.getNumberOfPlayers();
        userCreation.assigningNames();
        playerOne = userCreation.getPlayerOne();
        playerTwo = userCreation.getPlayerTwo();
        playerThree = userCreation.getPlayerThree();
        playerFour = userCreation.getPlayerFour();
        dealCards();
    }

    public void dealHands(String currentPlayer, String nextPlayer, List<Cards> currentPlayerCards) {
        for (int i = 0; i < 7; i++) {
            allCards.dealCard();
            dealtCard = allCards.getDealtCard();
            currentPlayerCards.add(dealtCard);
            allCards.getCardVisual(dealtCard);
        }
        System.out.println("\n" + currentPlayer + " your cards are: " + currentPlayerCards);

        PlayerInteraction.seenCardConfirmation(currentPlayer);
        PlayerInteraction.nextPlayer(currentPlayer, nextPlayer);
    }

    public void dealCards() {
        allCards.createAllCards();
        allCards.shuffle();

        PlayerInteraction.dealCards(playerOne);

            // dealing cards depending on number of players
            switch (numberOfPlayers) {
                case "2":
                    dealHands(playerOne, playerTwo, playerOneCards);
                    dealHands(playerTwo, playerOne, playerTwoCards);
                    break;
                case "3":
                    dealHands(playerOne, playerTwo, playerOneCards);
                    dealHands(playerTwo, playerOne, playerTwoCards);
                    dealHands(playerThree, playerOne, playerThreeCards);
                    break;
                case "4":
                    dealHands(playerOne, playerTwo, playerOneCards);
                    dealHands(playerTwo, playerOne, playerTwoCards);
                    dealHands(playerThree, playerOne, playerThreeCards);
                    dealHands(playerFour, playerOne, playerFourCards);

        }
        mainGamePlay();
    }

    public void playerTurn(String currentPlayer, String nextPlayer, List<Cards> currentPlayerCards) {
        System.out.println(currentPlayer + " your current cards are as followed: " + currentPlayerCards);
        for (Cards card : currentPlayerCards) {
            allCards.getCardVisual(card);
        }
        if (discardedPile.isEmpty()) {
            PlayerInteraction.firstPlayerGameDecision(currentPlayer);
            allCards.dealCard();
            dealtCard = allCards.getDealtCard();
            System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
            currentPlayerCards.add(dealtCard);

        } else {
            PlayerInteraction.playerGameDecision(currentPlayer, discardedPile.get(discardedPile.size() - 1));
            confirmation = PlayerInteraction.getConfirmation();

            if (Objects.equals(confirmation, "1")) {
                dealtCard = discardedPile.get(discardedPile.size() - 1);
                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                currentPlayerCards.add(dealtCard);

            } else {
                allCards.dealCard();
                dealtCard = allCards.getDealtCard();
                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                currentPlayerCards.add(dealtCard);
            }
        }
        System.out.println("You currently have the below cards: ");
        for (Cards card : currentPlayerCards) {
            allCards.getCardVisual(card);
        }
        PlayerInteraction.playerRemoveCardChoice(currentPlayer, currentPlayerCards);
        int confirmationInt = Integer.parseInt(PlayerInteraction.getConfirmation());
        discardedPile.add(currentPlayerCards.get(confirmationInt - 1));

        currentPlayerCards.remove(confirmationInt - 1);

        System.out.println("\n" + currentPlayer + " your new set is as below: ");
        for (Cards card : currentPlayerCards) {
            allCards.getCardVisual(card);
        }

        if (allCards.getDeckOfCards().isEmpty()) {
            allCards.refreshStack(discardedPile);
            discardedPile.clear();
        }

        PlayerInteraction.gameState(currentPlayer, nextPlayer, currentPlayerCards);
    }



    public void mainGamePlay() {
        boolean gameFinished = false;
        // main gameplay depending on number of players
        while (!gameFinished) {
            switch (numberOfPlayers) {
                case "2":
                    playerTurn(playerOne, playerTwo, playerOneCards);
                    playerTurn(playerTwo, playerOne, playerTwoCards);
                    gameFinished = PlayerInteraction.isGameFinished();
                    if(gameFinished) {
                        break;
                    }
                    break;
                case "3":
                    playerTurn(playerOne, playerTwo, playerOneCards);
                    playerTurn(playerTwo, playerThree, playerTwoCards);
                    playerTurn(playerThree, playerOne, playerThreeCards);
                    gameFinished = PlayerInteraction.isGameFinished();
                    if(gameFinished) {
                        break;
                    }
                    break;
                case "4":
                    playerTurn(playerOne, playerTwo, playerOneCards);
                    playerTurn(playerTwo, playerThree, playerTwoCards);
                    playerTurn(playerThree, playerFour, playerThreeCards);
                    playerTurn(playerFour, playerOne, playerThreeCards);
                    gameFinished = PlayerInteraction.isGameFinished();
                    if(gameFinished) {
                        break;
                    }
                    break;
            }
        }
    }

    @Override
        public void restart () {

            }
        }