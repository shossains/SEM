package scoring;

import gamelogic.Puck;

/**
 * The basic version of the scoring system for AirHockey game.
 * It works with a two player version of the game
 * on  a flat table with two goals on opposite ends.
 * The game ends when one of the players gathers eleven points or when the clock runs out.
 * The winner of the game is the player who first gathers eleven points or
 * the player who has more points when the clock runs out.
 * The game can end with a tie.
 */
public class BasicScoringSystem extends ScoringSystem {
    private static final int END_SCORE = 11;

    public BasicScoringSystem(Puck puck, Hud hud) {
        super(puck, hud);
    }

    /**
     * Checks if one of the players have scored a goal.
     * @return an integer representing the player. 0 otherwise.
     */
    @Override
    public int goal() {
        if (goalPlayerOne(puck)) {
            hud.addScoreOne();
            //Gdx.app.log("GOAL", "Player 1 scored");
            return 1;
        } else if (goalPlayerTwo(puck)) {
            hud.addScoreTwo();
            //Gdx.app.log("GOAL", "Player 2 scored");
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public boolean checkScorePlayerOne() {
        return (hud.getScoreOne() == END_SCORE);
    }

    @Override
    public boolean checkScorePlayerTwo() {
        return (hud.getScoreTwo() == END_SCORE);
    }

    /**
     * Check if the puck's in the PLayerTwos goal.
     * @param puck the games puck.
     * @return true if the puck is in PlayerTwos goal.
     */
    private boolean goalPlayerOne(Puck puck) {
        return (puck.x + (puck.radius / 2)  >= 1265
                && puck.y + (puck.radius / 2) >= 270
                && puck.y + (puck.radius / 2) <= 454);
    }

    /**
     * Check if the puck's in the PlayerOnes goal.
     * @param puck the games puck.
     * @return true if the puck is in PlayerOnes goal.
     */
    private boolean goalPlayerTwo(Puck puck) {
        return (puck.x - (puck.radius / 2) <= 15
                && puck.y - (puck.radius / 2)  >= 270
                && puck.y + (puck.radius / 2) <= 465);
    }


}
