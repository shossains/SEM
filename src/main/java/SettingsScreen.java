import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {

    final transient MyGdxGame game;

    public transient Stage stage;
    public transient ImageButton backButton;
    public transient ButtonFactory buttonFactory;
    public transient Label outputLabel;

    private transient boolean mutePressed;


    /**
     * Constructor for this Screen.
     * @param game the current game instance
     */
    public SettingsScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        buttonFactory = new ButtonFactory(game, this);
        backButton = buttonFactory.createTransImButton("assets/back.png", "MainMenuScreen");
        backButton.setPosition(220, 100);
        stage.addActor(backButton);

        game.font.setColor(Color.RED);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mutePressed = Gdx.input.isKeyJustPressed(Input.Keys.M);
        if (mutePressed) {
            game.muteUnmute();
        }

        Gdx.gl.glClearColor((float)204 / 255, (float)204 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        game.font.getData().setScale(1.6f);
        game.font.draw(game.spriteBatch, "Settings", 235, 450);
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
        stage.dispose();
    }
}
