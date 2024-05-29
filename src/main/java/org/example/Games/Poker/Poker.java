package org.example.Games.Poker;

import org.example.Games.Rummy.RummySetUp;
import org.example.SetUp.Cards;

import java.util.List;

public class Poker {
    private static final String title = "Texas Hold'em Poker";
    private static final String rules = "The aim of Texas Hold'em Poker is to win chips by either having the best hand or by convincing other players to fold.\n" +
            "\n" +
            "Gameplay consists of the following phases:\n" +
            "1: Pre-Flop: Each player is dealt two private cards (hole cards) that belong to them alone. Betting begins with the player to the left of the big blind.\n" +
            "\n" +
            "2: The Flop: Three community cards are dealt face-up on the 'board'. These are shared by all players. Another round of betting ensues, starting with the player to the left of the dealer.\n" +
            "\n" +
            "3: The Turn: A fourth community card is dealt face-up. Another round of betting follows.\n" +
            "\n" +
            "4: The River: The fifth and final community card is dealt face-up. The last round of betting takes place.\n" +
            "\n" +
            "5: The Showdown: If more than one player remains after the final betting round, players reveal their hole cards and the best five-card hand wins the pot. Hands are made using any combination of the five community cards and the two hole cards.\n" +
            "\n" +
            "Hand Rankings from highest to lowest are:\n" +
            "1: Royal Flush: A, K, Q, J, 10, all of the same suit.\n" +
            "2: Straight Flush: Five consecutive cards of the same suit.\n" +
            "3: Four of a Kind: Four cards of the same rank.\n" +
            "4: Full House: Three of a kind combined with a pair.\n" +
            "5: Flush: Any five cards of the same suit, not in sequence.\n" +
            "6: Straight: Five consecutive cards of different suits.\n" +
            "7: Three of a Kind: Three cards of the same rank.\n" +
            "8: Two Pair: Two different pairs.\n" +
            "9: One Pair: Two cards of the same rank.\n" +
            "10: High Card: When no other hand applies, the highest card wins.\n" +
            "\n" +
            "Each round starts with two mandatory bets called the small blind and the big blind, posted by the two players to the left of the dealer button.\n" +
            "The dealer button moves one seat to the left after each hand, rotating the responsibility of dealing and posting blinds.\n" +
            "Players may fold, call, or raise during each betting round.\n" +
            "The game continues until players decide to stop, or one player has won all the chips.";
}

