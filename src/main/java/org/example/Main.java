package org.example;

import org.example.Games.Rummy.Rummy;
import org.example.Games.Rummy.WinConfirmation;
import org.example.HomeScreen.HomeScreen;
import org.example.SetUp.Cards;
import org.example.SetUp.Rank;
import org.example.SetUp.Suit;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static List<Cards> testdeck = new ArrayList<>();
    static String plone = "molly";
    static String pltwo = "hpoe";

    public static void adding() {
        testdeck.add(new Cards(Suit.CLUBS, Rank.TWO));
        testdeck.add(new Cards(Suit.HEARTS, Rank.TWO));
        testdeck.add(new Cards(Suit.SPADES, Rank.TWO));
        testdeck.add(new Cards(Suit.DIAMONDS, Rank.SEVEN));
        testdeck.add(new Cards(Suit.HEARTS, Rank.EIGHT));
        testdeck.add(new Cards(Suit.CLUBS, Rank.EIGHT));
        testdeck.add(new Cards(Suit.SPADES, Rank.EIGHT));
    };

    public static void main(String[] args) {

//        adding();
//        WinConfirmation.checkingNumberOfSuits(testdeck, plone, pltwo);

        WinConfirmation.playerWon(plone, testdeck);
//        Rummy.playRummy();
//        HomeScreen.openHome();

    }
}