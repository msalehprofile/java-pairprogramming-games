package gameProject.Games.Rummy;

import gameProject.HomeScreen.HomeScreen;
import gameProject.SetUp.Cards;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PlayerInteraction {
    private static String confirmation;
    private static boolean gameFinished;

    public static boolean isGameFinished() {
        return gameFinished;
    }

    public static String getConfirmation() {
        return confirmation;
    }

    // passing go to next player
    public static void nextPlayer(String playersName, String nextPlayer) {
        System.out.println(playersName +" please can you pass the screen to " + nextPlayer + "\n" + "\n"
                + nextPlayer + " please confirm you now have the screen:\n"
                + "1: Confirmed.\n");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextLine();
        if(Objects.equals(confirmation, "1")){
            System.out.println("\n" + nextPlayer + " thank you for confirming.\n");
        } else {
            System.out.println("\nSorry I didn't catch that. \n");
            nextPlayer(playersName, nextPlayer);
        }
    }

    // player can only select unknown card as there are none in visible stack
    public static void firstPlayerGameDecision(String playersName) {
        System.out.println("\n " + playersName + " please pick up an unknown card\n"
                +"1: Select unknown card.\n");
        Scanner playerSelection = new Scanner(System.in);
        confirmation = playerSelection.nextLine();

        if(!Objects.equals(confirmation, "1")) {
            System.out.println("\nSorry I didn't catch that. \n");
            firstPlayerGameDecision(playersName);
        }
    }

    // player choses to continue or announce rummy
    public static void gameState(String playersName, String nextPlayer, List<Cards> playersFinalHand) {
        System.out.println("\n" + playersName + " please decide what you would like to do next\n"
                +"1: Continue Game \n"
                +"2: Announce Rummy\n"
                +"3: Restart Game\n"
                +"4: Exit Game\n");

        Scanner gameState = new Scanner(System.in);
        confirmation = gameState.nextLine();
        if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2") && !Objects.equals(confirmation, "3") && !Objects.equals(confirmation, "4")) {
            System.out.println("\nPlease chose a valid option.");
            gameState(playersName, nextPlayer, playersFinalHand);
        }

        if (Objects.equals(confirmation, "2")) {
            WinConfirmation.checkingNumberOfSuits(playersFinalHand, playersName, nextPlayer);
        } else if (Objects.equals(confirmation, "1")){
            PlayerInteraction.nextPlayer(playersName, nextPlayer);
        } else if (Objects.equals(confirmation, "3")){
            gameFinished = true;
            RummySetUp rummyRestart = new RummySetUp(Rummy.getTitle(), Rummy.getRules());
            rummyRestart.restart();
        } else {
            System.out.println("\nThank you for playing, see you again soon.");
            gameFinished = true;
            HomeScreen.openHome();
        }
    }

    // player can choose what card to pick up
    public static void playerGameDecision(String playersName, Cards visibleCard) {
        System.out.println("\n" + playersName + " please decide what you would like to do next\n"
        +"1: Select visible card: " + visibleCard + "\n"
        +"2: Select unknown card." +"\n");

        Scanner playerSelection = new Scanner(System.in);
        confirmation = playerSelection.nextLine();

        if (!Objects.equals(confirmation, "1") && !Objects.equals(confirmation, "2")) {
            System.out.println("\nPlease pick a valid option.");
            PlayerInteraction.playerGameDecision(playersName, visibleCard);
        }
    }

    // player choses what card they want to remove
    public static void playerRemoveCardChoice(String currentPlayer, List<Cards> playerCards) {
        System.out.println("\n" + currentPlayer + " please select what card you would like to remove from your deck: \n"
                + "1: " + playerCards.get(0) + "\n"
                + "2: " + playerCards.get(1) + "\n"
                + "3: " + playerCards.get(2) + "\n"
                + "4: " + playerCards.get(3) + "\n"
                + "5: " + playerCards.get(4) + "\n"
                + "6: " + playerCards.get(5) + "\n"
                + "7: " + playerCards.get(6) + "\n"
                + "8: " + playerCards.get(7) + "\n"
        );
        Scanner optChecker = new Scanner(System.in);
        confirmation = optChecker.nextLine();

        if (!confirmation.matches("\\d") || Integer.parseInt(confirmation) < 1 || Integer.parseInt(confirmation) > 8) {
            System.out.println("\nPlease chose a valid option");
            playerRemoveCardChoice(currentPlayer, playerCards);
        }
    }


    public static void dealCards(String player) {
        System.out.println("\n" + "We are now going to deal out the cards, " + player + " please take a look at the screen first.\n"
                + "\n" + "Select 1 to continue: \n" +
                "1: Continue");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextLine();

        if (!Objects.equals(confirmation, "1")) {
            System.out.println("\nPlease try again. ");
            dealCards(player);
        }

    }

    public static void playAgainDecider() {
        System.out.println("\nPlease decide whether you would like to play again: "
                + "\n1: Play again!"
                + "\n2: Exit game!");
        Scanner playerInput = new Scanner(System.in);
        String playAgainDecider = playerInput.nextLine();
        if(!Objects.equals(playAgainDecider, "2") && !Objects.equals(playAgainDecider, "1")) {
            System.out.println("\nPlease chose a valid option.");
            playAgainDecider();
        }
        if (Objects.equals(playAgainDecider, "1")) {
            Rummy.playRummy();
        } else if (Objects.equals(playAgainDecider, "2")) {
            gameFinished = true;
            System.out.println("Thank you for stopping by, see you soon!");
            HomeScreen.openHome();
        }
    }

    // player confirming they have seen their cards
    public static void seenCardConfirmation(String playersName) {
        System.out.println("\n" + playersName + " please confirm you have seen your cards: \n"
                + "1: Confirmed." +"\n");
        Scanner seenCards = new Scanner(System.in);
        confirmation = seenCards.nextLine();
        if(Objects.equals(confirmation, "1")){
            System.out.println("\n" + "Thank you for confirming "+ playersName  +"\n");
        } else {
            System.out.println("\nSorry I didn't catch that. \n");
            seenCardConfirmation(playersName);
        }
    }
}
