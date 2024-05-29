package org.example.Games.Poker;

import org.example.SetUp.AllCards;
import org.example.SetUp.Cards;
import org.example.SetUp.Game;
import org.example.SetUp.UserCreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokerLogic extends Game {
    private static final List<String> playerNames = new ArrayList<>();
    private List<Cards> gameCards;
    public static Cards dealtCards;
    static List<List<Cards>> playerHands;
    static AllCards deck = new AllCards();

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

    public PokerLogic(String title, String rules) {
        super(title, rules);
    }

    @Override
    public void play() {
        UserCreation userCreation = new UserCreation();
        userCreation.creatingUsers();
        numberOfPlayers = userCreation.getNumberOfPlayers();

        if (numberOfPlayers < 1 || numberOfPlayers > 4) {
            System.out.println("\nPlease select a valid option.");
            userCreation.creatingUsers();
            numberOfPlayers = userCreation.getNumberOfPlayers();
        }

        playerNames.clear();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
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
            for(int j = 0; j < 3; j++){
                deck.dealCard();
                playerHand.add(deck.getDealtCard());
            }
            playerHands.add(playerHand);
        }


    }
}
