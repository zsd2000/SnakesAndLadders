package core;

/**
 * This is the GameLogic class
 * This is essential to tie together: DieModel, PlayerModel/ComputerModel, and BoardModel
 * Using composition, this class handles how each of the mentioned classes interact
 * The main logical methods are: rollDie(), updatePosition(), switchTurn(), and isWon()
 * Those 4 methods are what allows the game to truly operate
 * The remaining methods are: the constructor and getters for the UI
 */

public class GameLogic {
    private final BoardModel board;
    private final DieModel die;
    private final PlayerModel player1;
    private final PlayerModel player2;
    private PlayerModel currentPlayer;
    private boolean gameOver;

    /**
     * This constructor sets up the game
     * It initializes the following: board, die, and sets player1's name to "P1"
     * It takes a Boolean parameter, passed from the user in UI, to determine whether a human or computer will be player
     * 2
     * If it's a human, player2's name is "P2"
     * If it's a computer, player2's name is "CP" for Computer Player
     * Also, it sets the currentPlayer to player1 and sets the gameOver Boolean to false, since the game is just starting
     * @param isHuman is a Boolean passed from the user, determining if player2 is a human or computer
     */
    public GameLogic(Boolean isHuman) {
        board = new BoardModel();
        die = new DieModel();
        player1 = new PlayerModel("P1");

        if (isHuman) {
            player2 = new PlayerModel("P2");
        } else {
            player2 = new ComputerPlayer();
        }
        currentPlayer = player1;
        gameOver = false;
    }

    /**
     * Calls the roll() method from the DieModel class to roll the die
     */
    public void rollDie() {
        die.roll();
    }

    /**
     * Updates the position of a player
     * First, the player's new position is temporarily held in a local variable, newPosition
     * If it's > 100, the method returns false, indicating that the player's position did not update
     * If it's <= 100, move() is called to actually update their position on the board
     * Then, their updated position is checked to see if there are any snakes or ladders on the spot, moving the player
     * accordingly if necessary
     * Lastly, the method returns true if the player moved
     * @return false/true depending upon the above scenario
     */
    public boolean updatePosition() {
        int newPosition = currentPlayer.getScore() + getLastRoll();

        if (newPosition > 100) { return false; }
        else {
            currentPlayer.move(getLastRoll());
            currentPlayer.setScore(board.getSnakeLadder(currentPlayer.getScore()));
            return true;
        }
    }

    /**
     * If the game is not over, the players switch
     * if the game is over, the winning player remains as the current player
     */
    public void switchTurn() {
        if (!gameOver) {
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }

    /**
     * A simple check to see if the current player has a score of 100
     * If they do, set gameOver to true
     * Else, set it to false
     * @return gameOver a Boolean value
     */
    public boolean isWon() {
        if (currentPlayer.getScore() == 100) {
            gameOver = true;
        } else {
            gameOver = false;
        }
        return gameOver;
    }

    /**
     * Returns the current player in the game
     * @return currentPlayer
     */
    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns if the game is over or not
     * @return gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns the last roll by the calling a DieModel method on die
     * @return int
     */
    public int getLastRoll() {
        return die.getValueRolled();
    }

    /**
     * Returns the board
     * @return board
     */
    public BoardModel getBoard() {
        return board;
    }

    /**
     * Returns player 1
     * @return player1
     */
    public PlayerModel getPlayer1() {
        return player1;
    }

    /**
     * Returns player 2
     * @return player2
     */
    public PlayerModel getPlayer2() {
        return player2;
    }
}