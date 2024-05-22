package org.example;

import org.example.SetUp.AllCards;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AllCards allCards = new AllCards();

        allCards.createAllCards();

        allCards.sortDeckByValue();
        allCards.shuffle();
        allCards.printCards();
        allCards.dealCard();
    }
}