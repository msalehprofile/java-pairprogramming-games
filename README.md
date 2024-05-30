# Game Project: Poker and Rummy :hearts: :clubs: :diamonds: :spades:

## Overview
Welcome to our Game Project! This project is a result of a collaborative pair-programming effort where Callum focused on developing the Poker game functionalities and Molly focused on developing the Rummy game functionalities. This repository contains the setup for a card game project, including classes for handling cards, suits, ranks, and user creation.

## Project Structure
The project is organized into the following packages and classes:

### Pair-Programming
This project involved pair-programming where we worked together on communal classes that we would both use in our games. Below are the classes that we each worked on:

#### Callum
- Cards
- Rank
- Suit
- README

#### Molly
- Game
- UserCreation
- HomeScreen

### Collaborative
- AllCards
  
## Poker
  - HandRank.java: An enum of all possible hand rankings used to evaluate players' hands.
  - PlayerInterface.java: Deals with all user interactions in rounds, buy-in, and all other cases.
  - Poker.java: Holds the rules, title, and order of commands to run the game.
  - PokerGame.java: Holds the code to loop the game when run.
  - PokerHandEvaluator.java: Holds all evaluation logic to determine what a winning hand is using the HandRank enum.
  - PokerLogic.java: Contains game logic like the dealing of cards and cycling of players to play the game. Also where the number of players is determined.
  - WinCase.java: Contains the logic to use the hand evaluator and hand rank above on each player hand and assign the winner with an index marker.
## Rummy
  - PlayerInteraction.java: Handles all user interactions with the game such as each player's next move.
  - Rummy.java: Holds the rules, title, and order of commands to run the game.
  - RummySetUp.java: Holds all the setup logic for rummy such as dealing cards and hands.
  - WinConfirmation.java: Holds the logic to determine who the winner is and when they win.
### HomeScreen
  - HomeScreen.Java: Creates the home screen and gives the user the choice of which game to play.

## gameProject.SetUp
- AllCards.java: Manages the creation, sorting, shuffling, and dealing of cards.
- Cards.java: Represents a single card with a suit and rank.
- Game.java: An abstract class defining the basic structure of a game.
- Rank.java: Enum representing the rank of a card.
- Suit.java: Enum representing the suit of a card.
- UserCreation.java: Handles the creation of users and assigning of player names.

### gameProject
- Main.java: Entry point of the application.

## Usage
Upon running the application, you will be presented with the home screen. Follow the prompts to select the game you want to play and the number of players. The user creation process will guide you through entering the names of the players.

## Features

## Poker (Implemented by Callum)
- Card Handling: Creation of a standard 52-card deck.
- Shuffling: Ability to shuffle the deck.
- Dealing: Deal cards to players and the game hand.
- Sorting: Sort the deck by value or suit for ranking.
- Display: Visual representation of cards.
- Chips: Allows players to raise, fold, call, or check with a poker chip economy.
- Replay-ability: A game can continue with the same players, maintaining necessary user info between rounds.

### Rummy (Implemented by Molly)
- Card Handling: Creation of a standard 52-card deck.
- Shuffling: Ability to shuffle the deck.
- Dealing: Deal cards to players.
- Sorting: Sort the deck by value or suit.
- Display: Visual representation of cards.
- Replay-ability: Players can start a new game when finished.
- Restart: Players can decide to restart the game at any point and cards will be dealt again.
- Hand Checks: When a player announces Rummy, the class checks whether they have a successful finishing hand. If not, turn is passed to the next player.
