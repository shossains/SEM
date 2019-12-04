import com.badlogic.gdx.Gdx;
import gamelogic.Puck;

public class ScoringSystem {

    private transient Puck puck;
    private transient Hud hud;

    public ScoringSystem(Puck puck, Hud hud) {
        this.puck = puck;
        this.hud = hud;
    }

    public boolean goal() {
        if (goalPlayer1(puck)) {
            hud.addScore1();
            Gdx.app.log("GOAL", "Player1");
            return true;
        } else if (goalPlayer2(puck)) {
            hud.addScore2();
            Gdx.app.log("GOAL", "Player2");
            return true;
        }
        return false;
    }

    public int getTheWinner() {
        if (hud.getScore1() > hud.getScore2()) {
            Gdx.app.log("END", "Player 1 won!");
            return 1;
        } else if (hud.getScore2() > hud.getScore1()) {
            Gdx.app.log("END", "Player 2 won!");
            return 2;
        } else {
            Gdx.app.log("END", "The game ended with a tie!");
            return 0;
        }
    }

    private boolean goalPlayer1(Puck puck) {
        return (puck.x + puck.radius >= 1260 && puck.y + puck.radius >= 255 && puck.y + puck.radius <= 465);
    }

    private boolean goalPlayer2(Puck puck) {
        return (puck.x + puck.radius <= 20 && puck.y + puck.radius >= 255 && puck.y + puck.radius <= 465);
    }



}
