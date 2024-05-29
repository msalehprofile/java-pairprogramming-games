package org.example.Games.Poker;

import org.example.SetUp.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHandEvaluator {
    static List<Cards> playerAndGameCards = new ArrayList<>();

    public static HandRank evaluateHand(List<Cards> playerCards, List<Cards> gameCards) {
        playerAndGameCards = combinePlayerAndGameCards(playerCards, gameCards);
        sortCards(playerAndGameCards);

        if (isRoyalFlush(playerAndGameCards)) return HandRank.ROYAL_FLUSH;
        if (isStraightFlush(playerAndGameCards)) return HandRank.STRAIGHT_FLUSH;
        if (isFourOfAKind(playerAndGameCards)) return HandRank.FOUR_OF_A_KIND;
        if (isFullHouse(playerAndGameCards)) return HandRank.FULL_HOUSE;
        if (isFlush(playerAndGameCards)) return HandRank.FLUSH;
        if (isStraight(playerAndGameCards)) return HandRank.STRAIGHT;
        if (isThreeOfAKind(playerAndGameCards)) return HandRank.THREE_OF_A_KIND;
        if (isTwoPair(playerAndGameCards)) return HandRank.TWO_PAIR;
        if (isOnePair(playerAndGameCards)) return HandRank.ONE_PAIR;

        return HandRank.HIGH_CARD;
    }

    public static List<Cards> combinePlayerAndGameCards(List<Cards> playerCards, List<Cards> gameCards) {
        List<Cards> combinedList = new ArrayList<>(playerCards);
        combinedList.addAll(gameCards);
        return combinedList;
    }

    private static void sortCards(List<Cards> cards) {
        cards.sort((a, b) -> a.getScore() - b.getScore());
    }

    private static boolean isRoyalFlush(List<Cards> cards) {
        return isStraightFlush(cards) && cards.get(cards.size() - 1).getScore() == 14;
    }

    private static boolean isStraightFlush(List<Cards> cards) {
        return isFlush(cards) && isStraight(cards);
    }

    private static boolean isFourOfAKind(List<Cards> cards) {
        return hasNOfAKind(cards, 4);
    }

    private static boolean isFullHouse(List<Cards> cards) {
        return hasNOfAKind(cards, 3) && hasNOfAKind(cards, 2);
    }

    private static boolean isFlush(List<Cards> cards) {
        return cards.stream().collect(Collectors.groupingBy(Cards::getSuit, Collectors.counting())).values().stream().anyMatch(count -> count >= 5);
    }

    private static boolean isStraight(List<Cards> cards) {
        List<Integer> scores = cards.stream().map(Cards::getScore).distinct().sorted().collect(Collectors.toList());
        if (scores.size() < 5) return false;

        for (int i = 0; i <= scores.size() - 5; i++) {
            if (scores.get(i + 4) - scores.get(i) == 4) return true;
        }

        // Special case: Ace can be low (A, 2, 3, 4, 5)
        if (scores.contains(14) && scores.subList(0, 4).equals(List.of(2, 3, 4, 5))) {
            return true;
        }

        return false;
    }

    private static boolean isThreeOfAKind(List<Cards> cards) {
        return hasNOfAKind(cards, 3);
    }

    private static boolean isTwoPair(List<Cards> cards) {
        return cards.stream().collect(Collectors.groupingBy(Cards::getScore, Collectors.counting())).values().stream().filter(count -> count == 2).count() >= 2;
    }

    private static boolean isOnePair(List<Cards> cards) {
        return hasNOfAKind(cards, 2);
    }

    private static boolean hasNOfAKind(List<Cards> cards, int n) {
        return cards.stream().collect(Collectors.groupingBy(Cards::getScore, Collectors.counting())).values().stream().anyMatch(count -> count == n);
    }
}
