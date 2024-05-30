package gameProject.Games.Poker;

import gameProject.SetUp.Cards;
// PokerGame.java
import gameProject.SetUp.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            PlayerInterface.roundInteraction(PokerLogic.getNumberOfPlayers());
            pokerLogic.dealFlop();
            PlayerInterface.roundInteraction(PokerLogic.getNumberOfPlayers());
            pokerLogic.dealTurn();
            PlayerInterface.roundInteraction(PokerLogic.getNumberOfPlayers());
            pokerLogic.dealRiver();
            PlayerInterface.roundInteraction(PokerLogic.getNumberOfPlayers());

            List<Cards> allCards = pokerLogic.getGameCards();
            List<List<Cards>> playerAndGameCards = PokerLogic.getPlayerHands();

            // Check if a player won through folding
            if (!PlayerInterface.playerWonRound) {
                WinCase winCase = new WinCase(allCards, playerAndGameCards, PokerLogic.playerNames);
            }

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
