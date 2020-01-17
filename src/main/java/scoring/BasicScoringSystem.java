package scoring;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import gui.GameScreen;
import gui.ScoresScreen;

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
    private static final int END_TIME = 0;

    private transient Sound sound;

    transient GameScreen gameScreen;


    /**
     * Constructor of the BasicScoringSystem.
     * @param hud Hud used during the game.
     * @param gameScreen The screen displayed with the game. Used here to pause and resume the game.
     */
    public BasicScoringSystem(Hud hud, GameScreen gameScreen, Sound sound) {
        super(hud);
        this.gameScreen = gameScreen;
        this.scorePlayerOne = 0;
        this.scorePlayerTwo = 0;
        this.sound = sound;
        this.justScored = false;
    }

    public void checkTime() {
        if (this.hud.getGameTimer() <= END_TIME) {
            endGame();
        }
    }

    @Override
    public void checkScorePlayerOne() {
        if (this.scorePlayerOne == END_SCORE) {
            endGame();
        }
    }

    @Override
    public void checkScorePlayerTwo() {
        if (this.scorePlayerTwo == END_SCORE) {
            endGame();
        }
    }

    private void endGame() {
        this.gameScreen.pause();
        int winner = getTheWinner();

        switch (winner) {
            case 1 : {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new ScoresScreen(this.gameScreen.game, scorePlayerOne * 10));
                break;
            }

            case 2: {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new ScoresScreen(this.gameScreen.game, scorePlayerTwo * 10));
                break;
            }

            default: {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new ScoresScreen(this.gameScreen.game, 100));
                break;
            }
        }
    }

    /**
     * This method adds one point to the pool of points of the Player One.
     * After doing that it pauses the state of the game,
     * resets the positions of paddles, then resumes the game.
     */
    public void goalPlayerOne() {
        this.scorePlayerOne++;
        sound.play();
        this.hud.modifyScoreOne(this.scorePlayerOne);
        this.gameScreen.pause();

        this.justScored = true;
        //not in the game screen
        //this.gameScreen.resetPaddles();
        this.gameScreen.resume();
    }

    /**
     * This method adds one point to the pool of points of the Player Two.
     * After doing that it pauses the state of the game,
     * resets the positions of paddles, then resumes the game.
     */
    public void goalPlayerTwo() {
        this.scorePlayerTwo++;
        sound.play();
        this.hud.modifyScoreTwo(this.scorePlayerTwo);
        this.gameScreen.pause();
        this.justScored = true;
        //this.gameScreen.resetPaddles();
        this.gameScreen.resume();
    }
}
