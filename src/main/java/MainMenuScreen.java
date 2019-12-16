import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    final transient MyGdxGame game;

    public transient String username;
    public transient Stage stage;
    public transient ImageButton playButton;
    public transient ImageButton settingsButton;
    public transient ImageButton logoutButton;
    public transient ImageButton exitButton;
    public transient ButtonFactory buttonFactory;
    public transient Label outputLabel;

    private transient boolean mutePressed;


    /**
     * Constructor for this Screen.
     * @param game the current game instance
     */
    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.buttonFactory = new ButtonFactory(this.game, this);
        playButton = buttonFactory.createTransitionImageButton("assets/play.png", "ChooseGameScreen");
        settingsButton = buttonFactory.createTransitionImageButton("assets/settings.png", "SettingsScreen");
        logoutButton = buttonFactory.createTransitionImageButton("assets/logout.png", "LoginScreen");
        exitButton = buttonFactory.createTransitionImageButton("assets/exit.png", "Exit");

        playButton.setPosition(230, 320);
        settingsButton.setPosition(230, 250);
        logoutButton.setPosition(230, 180);
        exitButton.setPosition(230, 110);

        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(logoutButton);
        stage.addActor(exitButton);


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
        game.font.getData().setScale(1.4f);
        game.font.draw(game.spriteBatch, "Welcome, " + username + " !!!", 230, 450);
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
        //stage.dispose();
    }
}
