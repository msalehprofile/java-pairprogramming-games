package org.example.Games.Poker;

import org.example.SetUp.Cards;
import org.example.SetUp.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Games.Poker.PokerLogic.getNumberOfPlayers;
import static org.example.Games.Poker.PokerLogic.playerNames;

public class PokerGame extends Game {
    public PokerGame(String title, String rules) {
        super(title, rules);
    }

    @Override
    public void play() {
            Scanner scanner = new Scanner(System.in);

            boolean playAnotherGame = true;
            PokerLogic pokerLogic = new PokerLogic();
            pokerLogic.play();
            while (playAnotherGame) {
                pokerLogic.setGameCards(new ArrayList<>());
                PokerLogic.setPlayerHands(new ArrayList<>());
                PlayerInterface.roundChips = 0;
                PlayerInterface.buyIn();
                pokerLogic.dealCards();
                PlayerInterface.roundInteraction(getNumberOfPlayers());
                pokerLogic.dealFlop();
                PlayerInterface.roundInteraction(getNumberOfPlayers());
                pokerLogic.dealTurn();
                PlayerInterface.roundInteraction(getNumberOfPlayers());
                pokerLogic.dealRiver();
                PlayerInterface.roundInteraction(getNumberOfPlayers());

                List<Cards> allCards = pokerLogic.getGameCards();
                List<List<Cards>> playerAndGameCards = PokerLogic.getPlayerHands();

                WinCase winCase = new WinCase(allCards, playerAndGameCards, playerNames);

                System.out.println("Do you want to play another game? (yes/no)");
                String playAnother = scanner.nextLine();
                playAnotherGame = playAnother.equalsIgnoreCase("yes");
                pokerLogic.cyclePlayers();
            }

            System.out.println("Thanks for playing! Goodbye.");
        }

    @Override
    public void restart() {
        return;
    }

}
