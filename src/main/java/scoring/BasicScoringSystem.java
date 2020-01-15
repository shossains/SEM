package scoring;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
    transient GameScreen gameScreen;

    /**
     * Constructor of the BasicScoringSystem.
     * @param hud Hud used during the game.
     * @param gameScreen The screen displayed with the game. Used here to pause and resume the game.
     */
    public BasicScoringSystem(Hud hud, GameScreen gameScreen) {
        super(hud);
        this.gameScreen = gameScreen;
        this.scorePlayerOne = 0;
        this.scorePlayerTwo = 0;
    }

//    /**
//     * Checks if one of the players have scored a goal.
//     * @return an integer representing the player. 0 otherwise.
//     */
//    @Override
//    public int goal() {
//        if (goalPlayerOne(puck)) {
//            hud.addScoreOne();
//            return 1;
//        } else if (goalPlayerTwo(puck)) {
//            hud.addScoreTwo();
//            return 2;
//        } else {
//            return 0;
//        }
//    }

    @Override
    public void checkTime() {
        if (this.hud.getGameTimer() <= END_TIME) {
           endGame();
        }
    }

    @Override
    public void checkScorePlayerOne() {
        System.out.println("SCORE PLAYER ONE: " + scorePlayerOne);
        if (this.scorePlayerOne == END_SCORE) {
           endGame();
        }
    }

    @Override
    public void checkScorePlayerTwo() {
        System.out.println("SCORE PLAYER TWO: " + scorePlayerTwo);

        if (this.scorePlayerTwo == END_SCORE) {
            endGame();
        }
    }

    private void endGame() {
        this.gameScreen.pause();
        ((Game) Gdx.app.getApplicationListener()).
                setScreen(new ScoresScreen(this.gameScreen.game, 100));
    }

    public void goalPlayerOne() {
        this.scorePlayerOne++;
        this.hud.modifyScoreOne(this.scorePlayerOne);
        this.gameScreen.pause();
        this.gameScreen.resetPaddles();
        this.gameScreen.resume();
    }

    public void goalPlayerTwo() {
        this.scorePlayerTwo++;
        this.hud.modifyScoreTwo(this.scorePlayerTwo);
        this.gameScreen.pause();
        this.gameScreen.resetPaddles();
        this.gameScreen.resume();
    }

   /* *//**
     * Check if the puck's in the PLayerTwos goal.
     * @param puck the games puck.
     * @return true if the puck is in PlayerTwos goal.
     *//*
    private boolean goalPlayerOne(Puck puck) {
        return (puck.x + (puck.radius / 2)  >= this.board.getGoal2().getDepth()
                && puck.y + (puck.radius / 2) >= this.board.getGoal2().getTopPost()
                && puck.y + (puck.radius / 2) <= this.board.getGoal1().getBottomPost());
    }

    *//**
     * Check if the puck's in the PlayerOnes goal.
     * @param puck the games puck.
     * @return true if the puck is in PlayerOnes goal.
     *//*
    private boolean goalPlayerTwo(Puck puck) {
        return (puck.x - (puck.radius / 2) <= this.board.getGoal1().getDepth()
                && puck.y - (puck.radius / 2)  >= this.board.getGoal1().getTopPost()
                && puck.y + (puck.radius / 2) <= this.board.getGoal1().getBottomPost());
    }*/
}
