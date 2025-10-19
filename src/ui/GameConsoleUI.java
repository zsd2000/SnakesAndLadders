package ui;

import core.ComputerPlayer;
import core.GameLogic;
import core.PlayerModel;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the GameConsoleUI class
 * This class ties together EVERYTHING
 * It allows the user to interact with the logic in GameLogic to play the game of Snakes and Ladders
 * The main two methods are: playGame() and printBoard()
 * playGame() handles 1 player's turn at a time
 * printBoard() does exactly that...prints the board
 * printWin() and welcome() are additional methods to make the main method prettier
 */
public class GameConsoleUI {
    private final Scanner scanner;
    private final GameLogic game;

    /**
     * This is the constructor, with a lot going on
     * First, the user input is taken, being either 1 or 2
     * We try the input to confirm it is an int, catching a mismatch if it is not
     * If option 1, we set isHuman to true and pass that to the GameLogic constructor, which is human vs human
     * If option 2, isHuman is false and GameLogic creates a human vs computer game
     * Lastly, if the catch is reached, a human vs computer is automatically created and a message is printed to the user
     */
    public GameConsoleUI() {
        scanner = new Scanner(System.in);
        boolean isHuman;
        int option = -1;

        try {
            option = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Incorrect input. 1 or 2 should have been entered. Defaulting to a Computer Player.");
            option = 2;
        }
        scanner.nextLine();
        if (option == 1) { isHuman = true; }
        else { isHuman = false; }
        game = new GameLogic(isHuman);
    }

    /**
     * The playGame() methods starts off with an if-else
     * If the currentPlayer is NOT an instance of ComputerPlayer, it prompts the user to hit enter, collects it,
     * and rolls the die
     * If the currentPlayer is an instance of ComputerPlayer, it automatically rolls the die
     * What the player rolled is shown
     * If the player rolled a value that causes their position to be > 100, a message is printed to let them know their
     * roll needs to equal 100
     * The board is printed and the winning condition is checked
     */
    public void playGame() {
        if (!(game.getCurrentPlayer() instanceof ComputerPlayer)) {
            System.out.print("Player " + game.getCurrentPlayer().getName() + " press <Enter> to roll.");

            scanner.nextLine();

            game.rollDie();
        }
        else {
            System.out.println("Computer is rolling");
            System.out.println("...");
            game.rollDie();
        }

        System.out.println(game.getCurrentPlayer().getName() + " rolled a " + game.getLastRoll());

        System.out.println();

        if (!game.updatePosition()) {
            System.out.println(game.getCurrentPlayer().getName() + " must land on 100 to win. Try again next turn.");
        }

        printBoard();

        if (game.isWon()) {
            printWin();
        }
        else {
            game.switchTurn();
        }
    }

    /**
     * This method prints the board using a nested for-loop
     * It checks for the following conditions:
     *  if player1 & player2's score == the board value, print "1/C" if player2 is the computer
     *  if player1 & player2's score == the board value, print "1/2"
     *  if player1 | player2's score == the board value, print "P1" | "P2"
     *  ELSE, print the applicable S for snake head, T for snake tail, L for ladder base, H for ladder top
     */
    public void printBoard() {
        int[][] board = game.getBoard().getBoard(); // 2D array

        PlayerModel player1 = game.getPlayer1();
        PlayerModel player2 = game.getPlayer2();

        String player1Name = player1.getName(); // 1st player's name
        String player2Name = player2.getName(); // 2nd player's name

        Map<Integer, Integer> snakes = game.getBoard().getSnakes();
        Map<Integer, Integer> ladders = game.getBoard().getLadders();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == player1.getScore() && board[i][j] == player2.getScore()) {
                    if (game.getPlayer2() instanceof ComputerPlayer) {
                        System.out.printf("|%3s", "1/C");
                    }
                    else {
                        System.out.printf("|%3s", "1/2");
                    }
                }
                else if (board[i][j] == player1.getScore()) {
                    System.out.printf("|%3s", player1Name);
                }
                else if (board[i][j] == player2.getScore()) {
                    System.out.printf("|%3s", player2Name);
                }
                else if (player1.getScore() == 100 && board[i][j] == 100) {
                    System.out.printf("|%3s", player1Name);
                }
                else if (player2.getScore() == 100 && board[i][j] == 100) {
                    System.out.printf("|%3s", player2Name);
                }

                else {
                    if (snakes.containsKey(board[i][j])) {
                        if (board[i][j] == 37) {
                            System.out.printf("|%3s", "S1");
                        } else if (board[i][j] == 73) {
                            System.out.printf("|%3s", "S2");
                        }
                    } else if (snakes.containsValue(board[i][j])) {
                        if (board[i][j] == 19) {
                            System.out.printf("|%3s", "T1");
                        } else if (board[i][j] == 51) {
                            System.out.printf("|%3s", "T2");
                        }
                    } else if (ladders.containsKey(board[i][j])) {
                        if (board[i][j] == 7) {
                            System.out.printf("|%3s", "L1");
                        } else if (board[i][j] == 64) {
                            System.out.printf("|%3s", "L2");
                        }
                    } else if (ladders.containsValue(board[i][j])) {
                        if (board[i][j] == 25) {
                            System.out.printf("|%3s", "H1");
                        } else if (board[i][j] == 86) {
                            System.out.printf("|%3s", "H2");
                        }
                    } else {
                        System.out.printf("|%3d", board[i][j]);
                    }
                }

                if (j == 9) {
                    System.out.println("|");
                }
            }
        }
    }

    /**
     * Print the player's name who won
     */
    public void printWin() {
        System.out.println(game.getCurrentPlayer().getName() + " won!");
    }

    /**
     * Print a welcome message and prompts the user for computer or human opponent
     */
    public static void welcome() {
        System.out.println("Welcome to my Snakes and Ladders game!");
        System.out.println("Press 1 to play against a human. Press 2 to play against a computer.");
    }


    public static void main(String[] args) {
        welcome();
        GameConsoleUI game = new GameConsoleUI();

        // While the game isn't over, continue to play
        while (!game.game.isGameOver()) {
            game.playGame();
        }
    }
}