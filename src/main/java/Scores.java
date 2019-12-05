import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import database.Query;

public class Scores implements Screen {

    final transient MyGdxGame game;
    public transient Stage stage;
    private transient AssetManager assetManager;
    final transient TextField usernameTextField;
    final transient String skinPath;
    final transient int score;

    /**
     * Constructor for Scores Screen.
     * @param game the current game.
     * @param score the score recieved from the GameScreen class.
     */
    public Scores(MyGdxGame game, int score) {
        this.game = game;
        this.score = score;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        assetManager = new AssetManager();
        assetManager.load("assets/uiskin.json", Skin.class);
        assetManager.finishLoading();
        skinPath = "assets/uiskin.json";
        Skin skin = new Skin(Gdx.files.internal(skinPath));
        usernameTextField = new TextField("", skin);
        usernameTextField.setPosition(250,200);
        stage.addActor(usernameTextField);
        TextButton button = new TextButton("Done!", skin);
        button.setPosition(100, 300);
        button.setSize(100, 50);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        checkScore(score);
                    }
                });
        stage.addActor(button);
    }

    /**
     * Method that shows the best scores.
     * @param score the score of the current player.
     */
    public void checkScore(int score) {
        String username = usernameTextField.getText();
        Query.getScore(username, score);
        String scores = Query.getTopScores();
        Dialog dialog = new Dialog("Top 5 scores",
                assetManager.get(skinPath, Skin.class),
                "dialog") {
        };
        dialog.setColor(Color.RED);
        dialog.setSize(600, 600);
        dialog.text(scores);
        dialog.button("Ok", false);
        dialog.show(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)1, (float)170 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        game.font.setColor(Color.ROYAL);
        game.font.draw(game.spriteBatch, "Please enter your nickname.", 250, 425);
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
