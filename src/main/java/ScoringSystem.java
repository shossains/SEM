import com.badlogic.gdx.Gdx;
import gamelogic.Puck;

public class ScoringSystem {

    private transient Puck puck;
    private transient Hud hud;

    public ScoringSystem(Puck puck, Hud hud) {
        this.puck = puck;
        this.hud = hud;
    }

    public void goal() {
        if (goalPlayer1(puck)) {
            hud.addScore1();
            Gdx.app.log("GOAL", "Player1");
        } else if (goalPlayer2(puck)) {
            hud.addScore2();
            Gdx.app.log("GOAL", "Player2");
        }
    }

    private boolean goalPlayer1(Puck puck) {
        return (puck.x > 1100);
    }

    private boolean goalPlayer2(Puck puck) {
        return (puck.x < 100);
    }

}
