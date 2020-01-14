package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * MainMenuScreen is a graphical user interface which
 * helps the player choose either playing,
 * going to settings, exiting the game or logging out.
 */
public class MainMenuScreen implements Screen {

    final transient AirHockeyGame game;

    public transient String username;
    public transient Stage stage;
    public transient Button playButton;
    public transient Button settingsButton;
    public transient Button logoutButton;
    public transient Button exitButton;
    public transient AbstractButtonFactory abstractButtonFactory;

    private transient boolean mutePressed;


    /**
     * Constructor for this Screen.
     * Here, the 4 main buttons are created,
     * which help the user choose where to go next:
     * either playing, going to settings screen, logging out
     * or exiting this page.
     * @param game the current game instance
     */
    public MainMenuScreen(AirHockeyGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.abstractButtonFactory = new ImageButtonFactory(game, this);
        playButton = abstractButtonFactory.createTransButton("assets/play.png", "ChooseGameScreen");
        settingsButton = abstractButtonFactory.createTransButton("assets/settings.png",
                "SettingsScreen");
        logoutButton = abstractButtonFactory.createTransButton("assets/logout.png", "LoginScreen");
        exitButton = abstractButtonFactory.createTransButton("assets/exit.png", "Exit");

        playButton.setPosition(230, 320);
        //settingsButton.setPosition(230, 250);
        logoutButton.setPosition(230, 250);
        exitButton.setPosition(230, 180);

        stage.addActor(playButton);
        //stage.addActor(settingsButton);
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
