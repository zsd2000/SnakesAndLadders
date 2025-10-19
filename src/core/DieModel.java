package core;

import java.util.Random;

/**
 * This is the DieModel class
 * It is responsible for the die in the Snakes and Ladder game
 * This class: creates the DieModel object, returns the sides of the die, rolls the die, and gets the value rolled
 * This class is essential to play Snakes and Ladders. It drives game play
 */

public class DieModel {
    private final Random rand = new Random();  // To randomize the value rolled
    private final int sides;    // The amount of sides the dice will have
    private int valueRolled;    // Shows the last value rolled

    /**
     * No-argument constructor. As our instructions prompt, our die will be 6-sided
     * This constructor sets our sides variable to 6
     */
    public DieModel() {
        this.sides = 6;
    }

    /**
     * This constructor is for expansion, in the event that our die value will not be 6
     * @param sides an int of how many sides will be on the die
     */
    public DieModel(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException("Die side must be greater than 0");
        }
        this.sides = sides;
    }

    /**
     * Getter method to return the amount of sides of the die
     * @return sides an int of the amount of faces of the die
     */
    public int getSides() {
        return sides;
    }

    /**
     * Using Random, this method rolls the die from 1 to the sides (int) of the die
     * It only stores the value rolled internally, but doesn't return it
     */
    public void roll() {
        valueRolled = rand.nextInt(sides) + 1;
    }

    /**
     * This method returns the value rolled by the die. This is the getter method to the roll() method
     * @return valueRolled the int rolled by the die
     */
    public int getValueRolled() {
        return valueRolled;
    }
}