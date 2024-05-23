package org.example.SetUp;

import java.util.Objects;
import java.util.Scanner;

public class UserCreation {
    private String playerOne;
    private String playerTwo;
    private String playerThree;
    private String playerFour;
    private int numberOfPlayers;

    public String getPlayerFour() {
        return playerFour;
    }

    public String getPlayerThree() {
        return playerThree;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void creatingUsers() {
        System.out.println(" \n"
                + " Please pick the number of players (Max four):\n"
                + "1: One\n"
                + "2: Two\n"
                + "3: Three\n"
                + "4: Four\n");
        Scanner userCreation = new Scanner(System.in);
        numberOfPlayers = userCreation.nextInt();
    }

    public void assigningNames() {
        System.out.println("\n" +"You have selected to play with " + numberOfPlayers + " people.");
        switch (numberOfPlayers) {
            case 1:
                assignPlayerOne();
                System.out.println(" \n"
                +"Welcome " + playerOne);
                break;
            case 2:
                assignPlayerOne();
                assignPlayerTwo();

                System.out.println(" \n"
                        +"Welcome " + playerOne + " and " + playerTwo);
                break;
            case 3:
                assignPlayerOne();
                assignPlayerTwo();
                assignPlayerThree();

                System.out.println(" \n"
                        +"Welcome " + playerOne + ", " + playerTwo +" and " + playerThree);
                break;
            case 4:
                assignPlayerOne();
                assignPlayerTwo();
                assignPlayerThree();
                assignPlayerFour();

                System.out.println(" \n"
                        +"Welcome " + playerOne + ", " + playerTwo +", " + playerThree + " and " + playerFour);
                break;
            default:
                System.out.println("\n" + "Please select a number 1 to 4.");
                creatingUsers();
                break;
        }
    }

    public void assignPlayerOne() {
        System.out.println(" \n"
                + "Player One please enter your name:");
        Scanner playerOneName = new Scanner(System.in);
        playerOne = playerOneName.nextLine();
    };

    public void assignPlayerTwo() {
        System.out.println(" \n"
                + "Player Two please enter your name:");
        Scanner playerTwoName = new Scanner(System.in);
        playerTwo = playerTwoName.nextLine();
    };

    public void assignPlayerThree() {
        System.out.println(" \n"
                + "Player Three please enter your name:");
        Scanner playerThreeName = new Scanner(System.in);
        playerThree = playerThreeName.nextLine();
    };

    public void assignPlayerFour() {
        System.out.println(" \n"
                + "Player Two please enter your name:");
        Scanner playerFourName = new Scanner(System.in);
        playerFour = playerFourName.nextLine();
    };




}
