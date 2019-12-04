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

public class Hud implements Disposable {

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
     * Hud constructor.
     *
     */
    public Hud(SpriteBatch spriteBatch) {
        gameTimer = 240;
        timeCount = 0;
        score1 = 0;
        score2 = 0;

        viewport = new FitViewport(MyGdxGame.S_WIDTH, MyGdxGame.S_HEIGHT, new OrthographicCamera());
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
     * Add 1 point to score1 (player1).
     */
    public void addScore1() {
        score1++;
        scoreLabel1.setText(String.format(scoreFormat, score1));
    }

    /**
     * Add 1 point to score2 (player2).
     */
    public void addScore2() {
        score2++;
        scoreLabel2.setText(String.format(scoreFormat, score2));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
