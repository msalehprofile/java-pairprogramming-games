package org.example.Games.Poker;

import org.example.SetUp.Cards;

import java.util.ArrayList;
import java.util.List;

import static org.example.Games.Poker.PokerHandEvaluator.evaluateHand;

public class WinCase {

    public WinCase(List<Cards> allCards, List<List<Cards>> playerHands, List<String> playerNames) {
        List<HandRank> bestPlayerHandRanks = new ArrayList<>();
        for (List<Cards> playerHand : playerHands) {
            HandRank handRank = evaluateHand(playerHand, allCards);
            bestPlayerHandRanks.add(handRank);
        }

        int winningPlayerIndex = 0;
        HandRank winningHandRank = bestPlayerHandRanks.get(0);
        for (int i = 1; i < bestPlayerHandRanks.size(); i++) {
            HandRank currentHandRank = bestPlayerHandRanks.get(i);
            if (currentHandRank.compareTo(winningHandRank) > 0) {
                winningHandRank = currentHandRank;
                winningPlayerIndex = i;
            }
        }

        System.out.println("The winner is " + playerNames.get(winningPlayerIndex) + " with a " +
                winningHandRank.getName());
    }
}
