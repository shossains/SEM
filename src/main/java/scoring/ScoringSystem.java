package scoring;

import gamelogic.Puck;

/**
 * Interface for different scoring systems implemented in the game.
 * Provides the basic constructor, and methods for the basic game logic.
 */
public abstract class ScoringSystem {

    protected transient Hud hud;
    public transient int scorePlayerOne;
    public transient int scorePlayerTwo;

    public ScoringSystem(Hud hud) {
        this.hud = hud;
    }


    /**
     * Check if the games clock's ran out.
     */
    public abstract void checkTime();

    /**
     * Check if PlayerOne gathered enough points to win the game.
     */
    public abstract void checkScorePlayerOne();

    /**
     * Check if PlayerTwo gathered enough points to win the game.
     */
    public abstract void checkScorePlayerTwo();

    /**
     * Compare the scores of the players and establish the winner.
     * @return an int representing the player who won. 0 if it is a tie.
     */
    public int getTheWinner() {
        if (this.scorePlayerOne > this.scorePlayerTwo) {
            return 1;
        } else if (this.scorePlayerTwo > this.scorePlayerOne) {
            return 2;
        } else {
            return 0;
        }
    }


}
