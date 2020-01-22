package scoring;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gui.AirHockeyGame;

public class Hud implements Disposable, HudInterface {

    private static final int ONE = 1;

    public transient Stage stage;
    private transient Viewport viewport;
    private transient Table table;
    private transient BitmapFont font;

    private transient Integer gameTimer;
    transient float timeCount;
    private transient Integer score1;
    private transient Integer score2;
    private transient int minutes;
    private transient int seconds;

    private transient Label timeLabel;
    private transient Label scoreLabel1;
    private transient Label scoreLabel2;

    private transient String scoreFormat;

    /**
     * Constructor for the basic HUD, with
     * clock and scores of both players.
     * @param spriteBatch spriteBatch used by the game.
     */
    public Hud(SpriteBatch spriteBatch) {
        gameTimer = 300;
        timeCount = 0;
        score1 = 0;
        score2 = 0;

        viewport = new FitViewport(AirHockeyGame.S_WIDTH,
                AirHockeyGame.S_HEIGHT, new OrthographicCamera());
        //System.out.println(Gdx.graphics.getWidth());
        stage = new Stage(viewport, spriteBatch);
        font = new BitmapFont();
        font.getData().setScale(2, 2);

        table = new Table();
        table.top();
        table.setFillParent(true);

        seconds = gameTimer % 60;
        minutes = gameTimer / 60;
        scoreFormat = "%02d";

        timeLabel = new Label(String.format(minutes + ":" + seconds),
                new Label.LabelStyle(font, Color.BLACK));
        scoreLabel1 = new Label(String.format(scoreFormat, score1),
                new Label.LabelStyle(font, Color.BLACK));
        scoreLabel2 = new Label(String.format(scoreFormat, score2),
                new Label.LabelStyle(font, Color.BLACK));

        table.add(scoreLabel1).expandX().padTop(45);
        table.add(timeLabel).expandX().padTop(45);
        table.add(scoreLabel2).expandX().padTop(45);

        stage.addActor(table);
    }

    /**
     * Update the displayed time.
     * @param dt time difference in number of seconds.
     */
    @Override
    public void updateTime(float dt) {
        timeCount += dt;
        // If at least 1 second has elapsed
        if (timeCount >= ONE) {
            gameTimer--;
            seconds = gameTimer % 60;
            minutes = gameTimer / 60;
            timeLabel.setText(String.format(minutes + ":" + seconds));
            timeCount = 0;
        }
    }

    /**
     * Modify the score of Player One by a specified amount.
     * @param newScore New score of the Player One.
     */
    @Override
    public void modifyScoreOne(int newScore) {
        scoreLabel1.setText(String.format(scoreFormat, newScore));
    }

    /**
     * Modify the score of Player Two by a specified amount.
     * @param newScore New score of the Player Two.
     */
    @Override
    public void modifyScoreTwo(int newScore) {
        scoreLabel2.setText(String.format(scoreFormat, newScore));
    }

    /**
     * Getter for the gameTimer.
     * @return amount of seconds left for the game.
     */
    public int getGameTimer() {
        return this.gameTimer;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}
