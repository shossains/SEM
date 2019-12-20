package scoring;

import gamelogic.Puck;

/**
 * Interface for different scoring systems implemented in the game.
 * Provides the basic constructor, and methods for the basic game logic.
 */
public abstract class ScoringSystem {

    protected transient Puck puck;
    protected transient Hud hud;

    public ScoringSystem(Puck puck, Hud hud) {
        this.puck = puck;
        this.hud = hud;
    }

    /**
     * Checks if one of the players scored a point.
     * @return an integer representing player who scored the point.
     */
    public abstract int goal();

    /**
     * Check if the games clock's ran out.
     * @return true if the game's ended.
     */
    public boolean checkIfGameEnded() {
        return (this.hud.getGameTimer() <= 0);
    }

    /**
     * Check if PlayerOne gathered enough points to win the game.
     * @return true if PlayerOne gathered enough points.
     */
    public abstract boolean checkScorePlayerOne();

    /**
     * Check if PlayerTwo gathered enough points to win the game.
     * @return true if PlayerTwo gathered enough points.
     */
    public abstract boolean checkScorePlayerTwo();

    /**
     * Compare the scores of the players and establish the winner.
     * @return an int representing the player who won. 0 if it is a tie.
     */
    public int getTheWinner() {
        if (hud.getScoreOne() > hud.getScoreTwo()) {
            return 1;
        } else if (hud.getScoreTwo() > hud.getScoreOne()) {
            return 2;
        } else {
            return 0;
        }
    }


}
