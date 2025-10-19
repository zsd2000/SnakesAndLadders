package core;

import java.util.HashMap;
import java.util.Map;

/**
 * This is BoardModel class
 * It is responsible for creating the board and knowing where Snakes and Ladders are placed on the board
 * The players exist/interact with this board to play the game
 * This class: initializes the board from 1 to 100 in a "snaking" method and has a Map of Snakes/Ladders
 */
public class BoardModel {
    private final int[][] board;
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    /**
     * This constructor takes no parameters
     * It instantiates the 10x10 int matrix, then calls the "initBoard()" method to fill the array with values from
     * 1 to 100
     * It also places the final positions of the Snakes and Ladders onto the board
     * This version will have the Snakes and Ladders at fixed positions each game
     */
    public BoardModel() {
        board = new int[10][10];
        initBoard();
        snakes.put(37, 19);
        snakes.put(73, 51);
        ladders.put(7, 25);
        ladders.put(64, 86);
    }

    /**
     * This method uses a nested for-loop to initialize the 10x10 int matrix
     * Position 100 is found at [0][0] and position 1 is found at [9][0]
     * The outer for-loop controls the rows
     * The inner for-loop controls the columns, where the numbers are actually input into the matrix
     * Immediately, we start at row = 9 - i (which starts from 0). This helps to put 1 at [9][0]
     * Within this inner loop, if the row is odd, we increment from LEFT to RIGHT. Row 9 goes from 1 to 10
     * If the row is even, we increment from RIGHT to LEFT. Row 8 goes from 20 to 11
     */
    public void initBoard() {
        int start = 1;  // Start value of Snakes and Ladders

        for (int i = 0; i < 10; i++) {  // Row control
            int row = 9 - i;    // Starts from the bottom and moves upward. Bottom = 9. Top = 0.

            // If row is odd (bottom = 9), increment from LEFT to RIGHT
            if (row % 2 != 0) {
                for (int j = 0; j < 10; j++) {  // Column control
                    board[row][j] = start;
                    start++;
                }
            }
            // If row is even (top = 0), increment from RIGHT to LEFT
            else {
                for (int j = 9; j >= 0; j--) {  // Column control
                    board[row][j] = start;
                    start++;
                }
            }
        }
    }

    /**
     * A getter method to return the whole board as a 10x10 matrix
     * @return board the 10x10 int array
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * This method is used to find a Snake or Ladder on the board
     * If the passed position lands on a Snake head, it returns the Snake's tail
     * If the passed position lands on a Ladder base, it returns the Ladder's top
     * If the passed position lands on neither, it returns their passed position
     * @param position the player's current position on the board
     * @return int the position of a snake, ladder, or normal spot on the board
     */
    public int getSnakeLadder(int position) {
        if (snakes.containsKey(position)) {
            return snakes.get(position);
        }
        else if (ladders.containsKey(position)) {
            return ladders.get(position);
        }
        else {
            return position;
        }
    }

    /**
     * A simple getter method to return the Map of Snakes
     * @return snakes a Map<Integer, Integer> of the Snake head and tail values
     */
    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    /**
     * A simple getter method to return the Map of Ladders
     * @return ladders a Map<Integer, Integer> of the Ladder base and top values
     */
    public Map<Integer, Integer> getLadders() {
        return ladders;
    }
}