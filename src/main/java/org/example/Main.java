package org.example;

import org.example.SetUp.AllCards;
import org.example.SetUp.UserCreation;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AllCards allCards = new AllCards();
        UserCreation userCreation = new UserCreation();

        userCreation.creatingUsers();
        userCreation.assigningNames();

//        allCards.createAllCards();
//
//        allCards.sortDeckByValue();
//        allCards.shuffle();
//        allCards.printCards();
//        allCards.dealCard();
//        allCards.printCards();
    }
}