package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import gamelogic.CollisionsEngine;
import gamelogic.Entity;
import gamelogic.GameContainer;
import gamelogic.Paddle;
import gamelogic.PlayerType;
import gamelogic.Puck;
import java.util.ArrayList;
import scoring.BasicScoringSystem;
import scoring.Board;
import scoring.Goal;
import scoring.Hud;



public class GameScreen implements Screen {

    private static final float WIDTH = 1280;
    private static final float HEIGHT = 720;

    private static final float BASIC_GOAL_DEPTH = 15;

    private static final float PADDLE_MAX_SPEED = 300;
    private static final float PADDLE_LOW_SPEED = 75;
    private static final float PADDLE_ACCELERATION = 10;
    public static final float PADDLE_PUCK_E = 0.8f;
    public static final float PUCK_WALL_E = 0.85f;

    public final transient AirHockeyGame game;

    transient Texture puckImage;
    transient Texture paddle1Image;
    transient Texture paddle2Image;
    transient Texture boardImage;
    transient Texture goalOneImage;
    transient Texture goalTwoImage;

    transient Hud hud;
    transient Goal goal1;
    transient Goal goal2;
    transient Board board;
    transient Puck puck;
    transient Paddle paddle1;
    transient Paddle paddle2;

    private transient GameContainer gameContainer;

    transient Stage stage;
    transient AbstractButtonFactory abstractButtonFactory;
    transient Button resumeButton;
    transient Button exitButton;

    transient CollisionsEngine collisionsEngine;
    transient BasicScoringSystem basicScoringSystem;

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

        boardImage = new Texture(Gdx.files.internal("assets/board2.png"));
        goalOneImage = new Texture(Gdx.files.internal("assets/leftGoal.png"));
        goalTwoImage = new Texture(Gdx.files.internal("assets/rightGoal.png"));
        puckImage = new Texture(Gdx.files.internal("assets/hockey-puck.png"));
        paddle1Image = new Texture(Gdx.files.internal("assets/redPaddle.png"));
        paddle2Image = new Texture(Gdx.files.internal("assets/bluePaddle.png"));


        ArrayList<Texture> textures = new ArrayList<>();

        textures.add(boardImage);
        textures.add(goalOneImage);
        textures.add(goalTwoImage);
        textures.add(puckImage);
        textures.add(paddle1Image);
        textures.add(paddle2Image);


        camera = new OrthographicCamera();
        //we can change the resolution to whatever is appropriate later
        camera.setToOrtho(false, WIDTH, HEIGHT);

        // Create the HUD
        hud = new Hud(game.spriteBatch);
        // Create the scoring system
        basicScoringSystem = new BasicScoringSystem(hud, this, scoreSound);

        // Create the goals
        goal1 = new Goal((HEIGHT / 3), 2 * (HEIGHT / 3),
                BASIC_GOAL_DEPTH, basicScoringSystem);
        goal2 = new Goal((HEIGHT / 3), 2 * (HEIGHT / 3),
                (WIDTH - BASIC_GOAL_DEPTH), basicScoringSystem);

        // Create the board
        board = new Board(0, 0, WIDTH, HEIGHT, goal1, goal2);

        //we should later change it to the resolution and so on...

        puck = new Puck(640f, 360f, 30f, 0f, 30f,
                5, WIDTH, HEIGHT, PUCK_WALL_E, collisionSound);

        paddle1 = new Paddle(1000f, 360f, 0f, 0f, 40f, 10, WIDTH, HEIGHT,
                PlayerType.PLAYER1, PADDLE_MAX_SPEED, PADDLE_ACCELERATION, PADDLE_LOW_SPEED);
        paddle2 = new Paddle(360, 360f, 0f, 0f, 40f, 10, WIDTH, HEIGHT,
                PlayerType.PLAYER2, PADDLE_MAX_SPEED, PADDLE_ACCELERATION, PADDLE_LOW_SPEED);
        ArrayList<Entity> entities = new ArrayList<>();

        entities.add(board);
        entities.add(goal1);
        entities.add(goal2);
        entities.add(puck);
        entities.add(paddle1);
        entities.add(paddle2);

        collisionsEngine = new CollisionsEngine(PADDLE_PUCK_E, collisionSound);

        gameContainer = new GameContainer(entities, textures, basicScoringSystem);

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


    /**
     * Reset the paddles on the board to their initial positions.
     */
    public void resetPaddles() {
        this.paddle1.setX(1000f);
        this.paddle1.setY(360f);
        this.paddle1.setXspeed(0);
        this.paddle1.setYspeed(0);

        this.paddle2.setX(360f);
        this.paddle2.setY(360f);
        this.paddle2.setXspeed(0);
        this.paddle2.setYspeed(0);
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
        puckImage.dispose();
        stage.dispose();
    }
}
