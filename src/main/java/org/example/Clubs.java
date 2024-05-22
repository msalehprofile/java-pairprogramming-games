package org.example;

public class Clubs extends Cards {
    private final String SUIT = "&diams;";
    private String symbol;
    private int value;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
