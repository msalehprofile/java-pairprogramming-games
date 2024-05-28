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
    private static String confirmation;
    public static Cards dealtCard;

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
        numberOfPlayers = userCreation.getNumberOfPlayers();

        if(Objects.equals(numberOfPlayers, "1")) {
            System.out.println("\n"
                    + "You can't play Rummy alone, please select again.");
            userCreation.creatingUsers();
        }

        if(Objects.equals(numberOfPlayers, "2") || Objects.equals(numberOfPlayers, "3")  || Objects.equals(numberOfPlayers, "4")) {
            userCreation.assigningNames();
        } else {
            System.out.println("\n"
                    + "Please select a valid option.");
            userCreation.creatingUsers();
        }


        playerOne = userCreation.getPlayerOne();
        playerTwo = userCreation.getPlayerTwo();
        playerThree = userCreation.getPlayerThree();
        playerFour = userCreation.getPlayerFour();
        dealCards();
    }



    public void dealCards() {

        allCards.createAllCards();
        allCards.shuffle();

        PlayerInteraction.dealCards(playerOne);
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextLine();

        if (!Objects.equals(confirmation, "1")) {
            System.out.println("\nPlease try again. ");
            PlayerInteraction.dealCards(playerOne);
            Scanner seenCardsTwo = new Scanner(System.in);
            confirmation = seenCardsTwo.nextLine();
        }

        if (Objects.equals(confirmation, "1")) {
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
                    dealHands(playerFour, playerOne, playerThreeCards);
            }



            // main gameplay depending on number of players
            while (true) {
                switch (numberOfPlayers) {
                    case "2":
                        // player ones turn
                        playerTurn(playerOne, playerTwo, playerOneCards);
                        playerTurn(playerTwo, playerOne, playerTwoCards);
                        break;
                    case "3":
                        playerTurn(playerOne, playerTwo, playerOneCards);
                        playerTurn(playerTwo, playerThree, playerTwoCards);
                        playerTurn(playerThree, playerOne, playerThreeCards);
                        break;
                    case "4":
                        playerTurn(playerOne, playerTwo, playerOneCards);
                        playerTurn(playerTwo, playerThree, playerTwoCards);
                        playerTurn(playerThree, playerFour, playerThreeCards);
                        playerTurn(playerFour, playerOne, playerThreeCards);
                        break;

                }
            }
        }
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
            Scanner playerSelection = new Scanner(System.in);
            confirmation = playerSelection.nextLine();
            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                System.out.println("\nPlease pick a valid option.");
                PlayerInteraction.playerGameDecision(currentPlayer, discardedPile.get(discardedPile.size() - 1));
                Scanner playerSelectionTwo = new Scanner(System.in);
                confirmation = playerSelectionTwo.nextLine();
            }

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
        Scanner playerRemoves = new Scanner(System.in);
        int confirmationInt = playerRemoves.nextInt();
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

    public void dealHands(String currentPlayer, String nextPlayer, List<Cards> currentPlayerCards) {
        for (int i = 0; i < 7; i++) {
            allCards.dealCard();
            dealtCard = allCards.getDealtCard();
            currentPlayerCards.add(dealtCard);
            allCards.getCardVisual(dealtCard);
        }
        System.out.println("\n" + currentPlayer + " your cards are: " + currentPlayerCards);

        PlayerInteraction.seenCardConfirmaion(currentPlayer);
        PlayerInteraction.nextPlayer(currentPlayer, nextPlayer);
    }

    @Override
    public void restart() {


    }
}
