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
        AllCards allCards = new AllCards();
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
                case "3":
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
                case "4":
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
                    case "2":
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

                        } else {
                            PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerOne, playerOneCards);
                        Scanner playerOneRemoves = new Scanner(System.in);
                        confirmation = playerOneRemoves.nextLine();
                        int confirmationInt = playerOneRemoves.nextInt();
                        discardedPile.add(playerOneCards.get(confirmationInt - 1));

                        playerOneCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerOne + " your new set is as below: ");
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
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

                        } else {
                            PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerTwo, playerTwoCards);
                        Scanner playerTwoRemoves = new Scanner(System.in);
                        confirmation = playerTwoRemoves.nextLine();
                        confirmationInt = playerTwoRemoves.nextInt();
                        discardedPile.add(playerTwoCards.get(confirmationInt - 1));

                        playerTwoCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerTwo + " your new set is as below: ");
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }
                        PlayerInteraction.gameState(playerTwo, playerOne, playerTwoCards);
                        break;
                    case "3":
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

                        } else {
                            PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerOne, playerOneCards);
                        Scanner threePPlayerOneRemoves = new Scanner(System.in);
                        confirmation = threePPlayerOneRemoves.nextLine();
                        confirmationInt = threePPlayerOneRemoves.nextInt();
                        discardedPile.add(playerOneCards.get(confirmationInt - 1));

                        playerOneCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerOne + " your new set is as below: ");
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
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

                        } else {
                            PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerTwo, playerTwoCards);
                        Scanner threePPlayerTwoRemoves = new Scanner(System.in);
                        confirmation = threePPlayerTwoRemoves.nextLine();
                        confirmationInt = threePPlayerTwoRemoves.nextInt();
                        discardedPile.add(playerTwoCards.get(confirmationInt - 1));

                        playerTwoCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerTwo + " your new set is as below: ");
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }
                        PlayerInteraction.gameState(playerTwo, playerThree, playerTwoCards);

                        // play three turn
                        System.out.println(playerThree + " your current cards are as followed: " + playerThreeCards);
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }
                        if (discardedPile.isEmpty()) {
                            PlayerInteraction.firstPlayerGameDecision(playerThree);
                            allCards.dealCard();
                            dealtCard = allCards.getDealtCard();
                            System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                            playerThreeCards.add(dealtCard);

                        } else {
                            PlayerInteraction.playerGameDecision(playerThree, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerThree, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionThree = new Scanner(System.in);
                                confirmation = playerSelectionThree.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerThreeCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerThreeCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerThree, playerThreeCards);
                        Scanner threePPlayerThreeRemoves = new Scanner(System.in);
                        confirmation = threePPlayerThreeRemoves.nextLine();
                        confirmationInt =  threePPlayerThreeRemoves.nextInt();
                        discardedPile.add(playerThreeCards.get(confirmationInt - 1));

                        playerThreeCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerThree + " your new set is as below: ");
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }
                        PlayerInteraction.gameState(playerThree, playerOne, playerThreeCards);
                        break;
                    case "4":
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

                        } else {
                            PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerOne, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerOneCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerOne, playerOneCards);
                        Scanner fourPPlayerOneRemoves = new Scanner(System.in);
                        confirmation = fourPPlayerOneRemoves.nextLine();
                        confirmationInt = fourPPlayerOneRemoves.nextInt();
                        discardedPile.add(playerOneCards.get(confirmationInt - 1));

                        playerOneCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerOne + " your new set is as below: ");
                        for (Cards card : playerOneCards) {
                            allCards.getCardVisual(card);
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

                        } else {
                            PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerTwo, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionTwo = new Scanner(System.in);
                                confirmation = playerSelectionTwo.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerTwoCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerTwo, playerTwoCards);
                        Scanner fourPPlayerTwoRemoves = new Scanner(System.in);
                        confirmation = fourPPlayerTwoRemoves.nextLine();
                        confirmationInt = fourPPlayerTwoRemoves.nextInt();
                        discardedPile.add(playerTwoCards.get(confirmationInt - 1));

                        playerTwoCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerTwo + " your new set is as below: ");
                        for (Cards card : playerTwoCards) {
                            allCards.getCardVisual(card);
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }
                        PlayerInteraction.gameState(playerTwo, playerThree, playerTwoCards);

                        // play three turn
                        System.out.println(playerThree + " your current cards are as followed: " + playerThreeCards);
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }
                        if (discardedPile.isEmpty()) {
                            PlayerInteraction.firstPlayerGameDecision(playerThree);
                            allCards.dealCard();
                            dealtCard = allCards.getDealtCard();
                            System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                            playerThreeCards.add(dealtCard);

                        } else {
                            PlayerInteraction.playerGameDecision(playerThree, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerThree, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionThree = new Scanner(System.in);
                                confirmation = playerSelectionThree.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerThreeCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerThreeCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerThree, playerThreeCards);
                        Scanner fourPPlayerThreeRemoves = new Scanner(System.in);
                        confirmation = fourPPlayerThreeRemoves.nextLine();
                        confirmationInt = fourPPlayerThreeRemoves.nextInt();
                        discardedPile.add(playerThreeCards.get(confirmationInt - 1));

                        playerThreeCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerThree + " your new set is as below: ");
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }
                        PlayerInteraction.gameState(playerThree, playerFour, playerThreeCards);

                        // player four turn
                        System.out.println(playerFour + " your current cards are as followed: " + playerFourCards);
                        for (Cards card : playerFourCards) {
                            allCards.getCardVisual(card);
                        }
                        if (discardedPile.isEmpty()) {
                            PlayerInteraction.firstPlayerGameDecision(playerFour);
                            allCards.dealCard();
                            dealtCard = allCards.getDealtCard();
                            System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                            playerFourCards.add(dealtCard);

                        } else {
                            PlayerInteraction.playerGameDecision(playerFour, discardedPile.get(discardedPile.size() - 1));
                            Scanner playerSelection = new Scanner(System.in);
                            confirmation = playerSelection.nextLine();
                            if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
                                System.out.println("\nPlease pick a valid option.");
                                PlayerInteraction.playerGameDecision(playerFour, discardedPile.get(discardedPile.size() - 1));
                                Scanner playerSelectionFour = new Scanner(System.in);
                                confirmation = playerSelectionFour.nextLine();
                            }
                            if (Objects.equals(confirmation, "1")) {
                                dealtCard = discardedPile.get(discardedPile.size() - 1);
                                System.out.println("\n" + " the card you picked up was: " + dealtCard);
                                playerFourCards.add(dealtCard);

                            } else {
                                allCards.dealCard();
                                dealtCard = allCards.getDealtCard();
                                System.out.println("\n" + " the unknown card you picked up was: " + dealtCard);
                                playerFourCards.add(dealtCard);
                            }
                        }
                        System.out.println("You currently have the below cards: ");
                        for (Cards card : playerThreeCards) {
                            allCards.getCardVisual(card);
                        }
                        PlayerInteraction.playerRemoveCardChoice(playerFour, playerFourCards);
                        Scanner fourPPlayerFourRemoves = new Scanner(System.in);
                        confirmation = fourPPlayerFourRemoves.nextLine();
                        confirmationInt = fourPPlayerFourRemoves.nextInt();
                        discardedPile.add(playerFourCards.get(confirmationInt - 1));

                        playerFourCards.remove(confirmationInt - 1);

                        System.out.println("\n" + playerFour + " your new set is as below: ");
                        for (Cards card : playerFourCards) {
                            allCards.getCardVisual(card);
                        }

                        if (allCards.getDeckOfCards().isEmpty()) {
                            allCards.refreshStack(discardedPile);
                            discardedPile.clear();
                        }
                        PlayerInteraction.gameState(playerFour, playerOne, playerFourCards);

                        break;
                }
            }
        }
    }

    @Override
    public void restart() {


    }
}
