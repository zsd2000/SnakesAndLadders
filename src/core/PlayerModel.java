package core;

/**
 * This is the PlayerModel class
 * It is the parent class to the ComputerPlayer class
 * It is responsible for creating a player in Snakes and Ladders
 * Each player has a name and a score
 * The player's score indicates where they are on the game board
 */

public class PlayerModel {
    private final String name;  // The player's name
    private int position;  // The player's score

    /**
     * This constructor takes a String as a parameter and returns a PlayerModel object
     * It sets the player's name to the passed parameter and also sets their score to 1
     * @param name the player's name as a String
     */
    public PlayerModel(String name) {
        this.name = name;
        this.position = 1;
    }

    /**
     * A simple getter method to return the name of the player
     * @return name the player's name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * This method will update the player's score
     * It adds the passed parameter to their current position
     * Their position is where they are at on the Snakes and Ladders board (between 1 - 100)
     * It takes an int, but returns void
     * @param score the int amount to update the player's position on the board
     */
    public void move(int score) {
        this.position += score;
    }

    /**
     * A simple setter method to set the player's score (position) to the passed int parameter
     * @param score the player's int position on the board
     */
    public void setScore(int score) {
        this.position = score;
    }

    /**
     * A simple getter method to return the player's score, which is their position on the board
     * @return position the player's int position on the board
     */
    public int getScore() {
        return position;
    }
}