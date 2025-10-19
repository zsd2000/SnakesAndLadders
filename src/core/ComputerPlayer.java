package core;

/**
 * This is the ComputerPlayer class
 * It inherits the same attributes/methods as its parent PlayerModel class
 * Its purpose is to call the parent constructor and set the name of the Computer player in the game
 * The Computer's name is "CP" (for Computer Player)
 */

public class ComputerPlayer extends PlayerModel {

    public ComputerPlayer() {
        super("CP");
    }
}