package org.example.SetUp;

public abstract class Game {
    private final String title;
    private final String rules;

    public Game(String title, String rules){
        this.title = title;
        this.rules = rules;
    }

    public void printName() {
        System.out.println("You have chosen to play: " + title);
    }

    public void printRules() {
        System.out.println(" \n"
                + rules);
    }

    public abstract void play();

    public abstract void restart();

}
