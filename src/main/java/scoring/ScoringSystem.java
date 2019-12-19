package scoring;

import gamelogic.Puck;

public class ScoringSystem {
    private static final int END_SCORE = 11;

    private transient Puck puck;
    private transient Hud hud;

    public ScoringSystem(Puck puck, Hud hud) {
        this.puck = puck;
        this.hud = hud;
    }

    /**
     * Checks if one of the players have scored a goal.
     * @return an integer representing the player. 0 otherwise.
     */
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

    public boolean checkIfGameEnded() {
        return (hud.getGameTimer() <= 0);
    }

    public boolean checkScorePlayerOne() {
        return (hud.getScoreOne() == END_SCORE);
    }

    public boolean checkScorePlayerTwo() {
        return (hud.getScoreTwo() == END_SCORE);
    }

    /**
     * Compare the scores of the players and establish the winner.
     * @return an int representing the player who won. 0 if it is a tie.
     */
    public int getTheWinner() {
        if (hud.getScoreOne() > hud.getScoreTwo()) {
            //Gdx.app.log("END", "Player3 1 wins!");
            return 1;
        } else if (hud.getScoreTwo() > hud.getScoreOne()) {
            //Gdx.app.log("END", "Player 2 wins!");
            return 2;
        } else {
            //Gdx.app.log("END", "The game ended with a tie!");
            return 0;
        }
    }

    private boolean goalPlayerOne(Puck puck) {
        return (puck.x + (puck.radius / 2)  >= 1265
                && puck.y + (puck.radius / 2) >= 270
                && puck.y + (puck.radius / 2) <= 454);
    }

    private boolean goalPlayerTwo(Puck puck) {
        return (puck.x - (puck.radius / 2) <= 15
                && puck.y - (puck.radius / 2)  >= 270
                && puck.y + (puck.radius / 2) <= 465);
    }


}
