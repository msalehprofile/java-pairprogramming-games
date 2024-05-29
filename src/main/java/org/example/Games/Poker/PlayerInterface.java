package org.example.Games.Poker;

import static org.example.Games.Poker.PokerLogic.getNumberOfPlayers;
import static org.example.Games.Poker.PokerLogic.numberOfPlayers;

public class PlayerInterface {
    int numberOfPlayers = getNumberOfPlayers();
    int activePlayers = numberOfPlayers;
    protected static void roundInteraction(int numberOfPlayers){
        for (int i = 0; i < numberOfPlayers; i++) {

        }
    }
}
