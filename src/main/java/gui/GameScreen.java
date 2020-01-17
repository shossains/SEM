package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import gamelogic.LocalGameContainer;
import gamelogic.LocalGameFactory;
import scoring.Hud;

public class GameScreen implements Screen {

    private static final float WIDTH = 1280;
    private static final float HEIGHT = 720;


    public final transient AirHockeyGame game;

    transient Hud hud;

    private transient LocalGameContainer gameContainer;

    transient Stage stage;
    transient AbstractButtonFactory abstractButtonFactory;
    transient Button resumeButton;
    transient Button exitButton;

    transient OrthographicCamera camera;

    private transient boolean mutePressed;

    private transient boolean escPressed;
    private transient State state = State.RUN;

    private transient Sound collisionSound;
    private transient Sound scoreSound;

    public enum  State {
        PAUSE,
        RUN
    }

    /**
     * Constructor.
     * @param game The game object.
     */
    public GameScreen(final AirHockeyGame game) {
        this.game = game;

        //initialize sounds
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("assets/collide.wav"));
        scoreSound = Gdx.audio.newSound(Gdx.files.internal("assets/score.wav"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        abstractButtonFactory = new ImageButtonFactory(game, this);
        resumeButton = abstractButtonFactory.createButton("assets/resume.png");
        resumeButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        resume();
                    }
                });

        exitButton = abstractButtonFactory.createTransButton("assets/exit.png", "MainMenuScreen");

        stage.addActor(resumeButton);
        stage.addActor(exitButton);


        camera = new OrthographicCamera();
        //we can change the resolution to whatever is appropriate later
        camera.setToOrtho(false, WIDTH, HEIGHT);

        hud = new Hud(game.spriteBatch);

        LocalGameFactory gameFactory = new LocalGameFactory();
        gameContainer = gameFactory.createGameContainer(scoreSound, collisionSound, this, hud);
        //background colour
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        escPressed = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);

        switch (state) {
            case RUN:
                update(delta);
                draw();
                if (escPressed) {

                    pause();
                }
                break;
            case PAUSE:
                update(0);
                stage.draw();
                if (escPressed) {
                    resume();

                }
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called while the game is running.
     */
    public void update(float delta) {
        //update the camera
        camera.update();

        gameContainer.update(delta);

        gameContainer.collideEntities();

        gameContainer.updateAfterGoal();
    }

    /**
     * Method that is ran in order to render what is happening.
     */
    public void draw() {

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.spriteBatch.setProjectionMatrix(camera.combined);

        game.spriteBatch.begin();

        gameContainer.render(game);

        game.spriteBatch.end();
        // Draw the hud on top of the board.
        game.spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        resumeButton.setPosition(540, 430);
        exitButton.setPosition(540, 300);
        stage.act();
        stage.draw();
        this.state = State.PAUSE;
    }

    @Override
    public void resume() {
        resumeButton.setPosition(-1000, -1000);
        exitButton.setPosition(-1000, -1000);
        stage.act();
        stage.draw();
        this.state = State.RUN;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        scoreSound.dispose();
        collisionSound.dispose();
        stage.dispose();
    }
}
