import com.badlogic.gdx.Gdx;
import gamelogic.Puck;

public class ScoringSystem {

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
        if (goalPlayer1(puck)) {
            hud.addScore1();
            Gdx.app.log("GOAL", "Player 1 scored");
            return 1;
        } else if (goalPlayer2(puck)) {
            hud.addScore2();
            Gdx.app.log("GOAL", "Player 2 scored");
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Compare the scores of the players and establish the winner.
     * @return an int representing the player who won. 0 if it is a tie.
     */
    public int getTheWinner() {
        if (hud.getScore1() > hud.getScore2()) {
            Gdx.app.log("END", "Player 1 wins!");
            return 1;
        } else if (hud.getScore2() > hud.getScore1()) {
            Gdx.app.log("END", "Player 2 wins!");
            return 2;
        } else {
            Gdx.app.log("END", "The game ended with a tie!");
            return 0;
        }
    }

    private boolean goalPlayer1(Puck puck) {
        return (puck.x + (puck.radius / 2)  >= 1265
                && puck.y + (puck.radius / 2) >= 270
                && puck.y + (puck.radius / 2) <= 454);
    }

    private boolean goalPlayer2(Puck puck) {
        return (puck.x - (puck.radius / 2) <= 15
                && puck.y - (puck.radius / 2)  >= 270
                && puck.y + (puck.radius / 2) <= 465);
    }


}
