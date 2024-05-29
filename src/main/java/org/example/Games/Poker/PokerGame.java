package org.example.Games.Poker;

import org.example.SetUp.Cards;
import org.example.SetUp.AllCards;

import java.util.List;

import static org.example.Games.Poker.PokerLogic.getNumberOfPlayers;

public class PokerGame {
    public static void main(String[] args) {
        PokerLogic pokerLogic = new PokerLogic();
        pokerLogic.play(); // Initialize the game
        PlayerInterface.buyIn();
        pokerLogic.dealCards();
        PlayerInterface.roundInteraction(getNumberOfPlayers());
        pokerLogic.dealFlop();
        PlayerInterface.roundInteraction(getNumberOfPlayers());
        pokerLogic.dealTurn();
        PlayerInterface.roundInteraction(getNumberOfPlayers());
        pokerLogic.dealRiver();
        PlayerInterface.roundInteraction(getNumberOfPlayers());
    }
}
