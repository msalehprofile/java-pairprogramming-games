package gameProject.Games.Poker;

import gameProject.SetUp.AllCards;
import gameProject.SetUp.Cards;
import gameProject.SetUp.UserCreation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static gameProject.Games.Poker.PlayerInterface.displayPlayerHand;

public class PokerLogic {
    protected static final List<String> playerNames = new ArrayList<>();
    private List<Cards> gameCards = new ArrayList<>();
    public static Cards dealtCards;
    static List<List<Cards>> playerHands = new ArrayList<>();
    static AllCards deck = new AllCards();
    static List<Cards> discardedPile = new ArrayList<>();
    public static ArrayList<Integer> getPlayerChips() {
        return playerChips;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

        public static void setPlayerChips(ArrayList<Integer> playerChips) {
        PokerLogic.playerChips = playerChips;
    }

    static ArrayList<Integer> playerChips = new ArrayList<>();
    public static ArrayList<Boolean> isPlayerActive = new ArrayList<>();

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static void setNumberOfPlayers(int numberOfPlayers) {
        PokerLogic.numberOfPlayers = numberOfPlayers;
    }

    public static List<List<Cards>> getPlayerHands() {
        return playerHands;
    }

    public static void setPlayerHands(List<List<Cards>> playerHands) {
        PokerLogic.playerHands = playerHands;
    }

    public static Cards getDealtCards() {
        return dealtCards;
    }

    public static void setDealtCards(Cards dealtCards) {
        PokerLogic.dealtCards = dealtCards;
    }

    public List<Cards> getGameCards() {
        return gameCards;
    }

    public void setGameCards(List<Cards> gameCards) {
        this.gameCards = gameCards;
    }

    public static int numberOfPlayers;

    public void play() {
        UserCreation userCreation = new UserCreation();
        userCreation.pokerCreatingUsers();
        numberOfPlayers = Integer.parseInt(userCreation.getNumberOfPlayers());

        if (numberOfPlayers < 2 || numberOfPlayers > 6) {
            System.out.println("\nPlease select a valid option.");
            userCreation.pokerCreatingUsers();
            numberOfPlayers = Integer.parseInt(userCreation.getNumberOfPlayers());
        }

        playerNames.clear();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
            boolean add = isPlayerActive.add(true);
            playerChips.add(500);
        }
        System.out.println("Players in the game:");
        for (String name : playerNames) {
            System.out.println(name);
        }
    }

    public void dealCards(){
        if (deck.getDeckOfCards().size() < 13) {
            AllCards deck = new AllCards();
            deck.createAllCards();
            deck.shuffle();
        }
        for(int i = 0; i < numberOfPlayers; i++){
            List<Cards> playerHand = new ArrayList<>();
            for(int j = 0; j < 2; j++){
                deck.dealCard();
                playerHand.add(deck.getDealtCard());
            }
            playerHands.add(playerHand);
        }


    }

    public void cyclePlayers() {
        if (!playerNames.isEmpty()) {
            // Rotate player names
            Collections.rotate(playerNames, -1);
            // Rotate player chips
            Collections.rotate(playerChips, -1);
            // Rotate player active status
            Collections.rotate(isPlayerActive, -1);
        }
    };

    public void dealFlop() {
        for(int j = 0; j < 3; j++){
            deck.dealCard();
            gameCards.add(deck.getDealtCard());
            displayPlayerHand(gameCards);
        }
    }

    public void dealTurn() {
        deck.dealCard();
        gameCards.add(deck.getDealtCard());
        displayPlayerHand(gameCards);
    }
    public void dealRiver(){
        deck.dealCard();
        gameCards.add(deck.getDealtCard());
        displayPlayerHand(gameCards);
    }
}
