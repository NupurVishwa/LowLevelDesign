package Service;

import Entity.Board;
import Entity.Ladder;
import Entity.Player;
import Entity.Snake;

import Factory.DiceFactory;
import Factory.StandardDiceFactory;


import Strategy.Dice;

import Entity.GameState;

public class SnakeLadderGameDemo {

    public static void main(String[] args) {

        // =========================================
        // CREATE BOARD
        // =========================================

        Board board = new Board(100);


        // =========================================
        // ADD SNAKES
        // =========================================

        board.addSnake(new Snake(99, 10));

        board.addSnake(new Snake(95, 75));

        board.addSnake(new Snake(62, 18));

        board.addSnake(new Snake(47, 26));


        // =========================================
        // ADD LADDERS
        // =========================================

        board.addLadder(new Ladder(4, 25));

        board.addLadder(new Ladder(13, 46));

        board.addLadder(new Ladder(33, 49));

        board.addLadder(new Ladder(50, 69));

        board.addLadder(new Ladder(63, 81));


        // =========================================
        // FACTORY METHOD
        // =========================================

        DiceFactory diceFactory = new StandardDiceFactory(1);

        /*
         * Factory Method
         */
        Dice dice = diceFactory.createDice();


        // =========================================
        // CREATE GAME
        // =========================================

        SnakeLadderGame game = new SnakeLadderGame(board, dice);


        // =========================================
        // ADD PLAYERS
        // =========================================

        game.addPlayer(new Player("P1", "Nupur"));

        game.addPlayer(new Player("P2", "Rahul"));

        game.addPlayer(new Player("P3", "Priya"));


        // =========================================
        // START GAME
        // =========================================

        game.startGame();


        // =========================================
        // PLAY GAME
        // =========================================

        while (game.getState() != GameState.FINISHED) {

            game.playTurn();
        }


        // =========================================
        // DISPLAY WINNER
        // =========================================

        Player winner = game.getWinner();

        System.out.println();

        System.out.println("Game Winner: " + winner.getName());
    }
}