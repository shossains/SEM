package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * MainMenuScreen is a graphical user interface which
 * helps the player choose either playing,
 * going to settings, exiting the game or logging out.
 */
public class MainMenuScreen implements Screen {

    final transient AirHockeyGame game;

    public transient String username;
    private transient Stage stage;
    private transient Button playButton;
    private transient Button settingsButton;
    private transient Button logoutButton;
    private transient Button exitButton;
    private transient Button scoresButton;
    private transient AbstractButtonFactory abstractButtonFactory;

    private transient Image mtoMute;
    private transient Image escToPause;
    private transient Image p1Controls;
    private transient Image p2Controls;
    private transient Image arrowKeys;
    private transient Image wasdKeys;


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
        scoresButton = abstractButtonFactory.createTransButton("assets/scores.png", "Scores");


        playButton.setPosition(230, 420);
        settingsButton.setPosition(230, 350);
        logoutButton.setPosition(230, 310);
        exitButton.setPosition(230, 210);
        scoresButton.setPosition(530, 540);

        stage.addActor(playButton);
        //stage.addActor(settingsButton);
        stage.addActor(logoutButton);
        stage.addActor(exitButton);
        stage.addActor(scoresButton);

        game.font.setColor(Color.RED);

        //initialize images
        mtoMute = new Image(new Texture("assets/MtoMute.png"));
        escToPause = new Image(new Texture("assets/ESCtoPause.png"));
        p1Controls = new Image(new Texture("assets/player1controls.png"));
        p2Controls = new Image(new Texture("assets/player2controls.png"));
        arrowKeys = new Image(new Texture("assets/arrowKeys.png"));
        wasdKeys = new Image(new Texture("assets/WSAD.png"));

        //set image position
        mtoMute.setPosition(830, 650);
        escToPause.setPosition(830, 600);
        p1Controls.setPosition(830, 550);
        arrowKeys.setPosition(900, 350);
        p2Controls.setPosition(830, 270);
        wasdKeys.setPosition(900, -10);

        //set image sizes
        mtoMute.setScale(0.7f);
        escToPause.setScale(0.7f);
        p1Controls.setScale(0.7f);
        p2Controls.setScale(0.7f);
        wasdKeys.setScale(0.3f);
        arrowKeys.setScale(0.28f);

        //add images to stage
        stage.addActor(mtoMute);
        stage.addActor(escToPause);
        stage.addActor(p1Controls);
        stage.addActor(p2Controls);
        stage.addActor(arrowKeys);
        stage.addActor(wasdKeys);


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
        game.font.draw(game.spriteBatch, "Welcome, " + username + " !!!", 230, 600);
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
