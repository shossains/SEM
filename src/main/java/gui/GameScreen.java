package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import scoring.Hud;



public class GameScreen implements Screen {

    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    private static final float width = 1280;
    private static final float height = 720;

    private static final float paddleMaxSpeed = 300;
    private static final float paddleLowSpeed = 75;
    private static final float paddleAcceleration = 10;
    public static final float paddlePucke = 0.8f;
    public static final float puckWalle = 0.85f;

    final transient AirHockeyGame game;

    transient Texture puckImage;
    transient Texture paddle1Image;
    transient Texture paddle2Image;
    transient Texture boardImage;

    transient Hud hud;
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

        boardImage = new Texture(Gdx.files.internal("assets/table.png"));
        puckImage = new Texture(Gdx.files.internal("assets/hockey-puck.png"));
        paddle1Image = new Texture(Gdx.files.internal("assets/redPaddle.png"));
        paddle2Image = new Texture(Gdx.files.internal("assets/bluePaddle.png"));

        ArrayList<Texture> textures = new ArrayList<>();

        textures.add(boardImage);
        textures.add(puckImage);
        textures.add(paddle1Image);
        textures.add(paddle2Image);

        //gameContainer = new GameContainer();

        camera = new OrthographicCamera();
        //we can change the resolution to whatever is appropriate later
        camera.setToOrtho(false, width, height);

        // Create the board
        board = new Board(0, 0, width, height);

        // Create the HUD
        hud = new Hud(game.spriteBatch);

        //we should later change it to the resolution and so on...
        puck = new Puck(640f, 360f, 30f, 0f, 30f, 5, width, height, puckWalle);

        paddle1 = new Paddle(1000f, 360f, 0f, 0f, 40f, 10, width, height,
                PlayerType.PLAYER1, paddleMaxSpeed, paddleAcceleration, paddleLowSpeed);
        paddle2 = new Paddle(360, 360f, 0f, 0f, 40f, 10, width, height,
                PlayerType.PLAYER2, paddleMaxSpeed, paddleAcceleration, paddleLowSpeed);

        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(board);
        entities.add(puck);
        entities.add(paddle1);
        entities.add(paddle2);

        gameContainer = new GameContainer(entities, textures);

        collisionsEngine = new CollisionsEngine(paddlePucke);
        basicScoringSystem = new BasicScoringSystem(puck, hud);

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

        // Updated the game clock
        //hud.updateTime(delta);
        //move the puck
        ///puck.move(delta);
        //ensure it is within boundaries
        ///puck.fixPosition();

        // Check if the puck's in one of the goals
        /*
        int goal = basicScoringSystem.goal();
        if (goal != 0) {
            if (goal == PLAYER_ONE) {
                resetRight();
            } else if (goal == PLAYER_TWO) {
                resetLeft();
            }
        }

        // Check if the game's timer haven't run out
        if (basicScoringSystem.checkIfGameEnded()) {
            Gdx.app.log("END", "The timer run out");
            pause();
            basicScoringSystem.getTheWinner();
            ((Game)Gdx.app.getApplicationListener()).setScreen(new
                    ScoresScreen(game, 100));
        }

        // Check if one of the players wont the game
        if (basicScoringSystem.checkScorePlayerOne()) {
            pause();
            Gdx.app.log("END", "Player 1 wins");
            ((Game)Gdx.app.getApplicationListener()).setScreen(new
                    ScoresScreen(game, 100));
        } else if (basicScoringSystem.checkScorePlayerTwo()) {
            pause();
            Gdx.app.log("END", "Player 2 wins");
            ((Game)Gdx.app.getApplicationListener()).setScreen(new
                    ScoresScreen(game, 100));
        }
        */

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
     * Reset the board after player2 scored.
     * The puck moves towards the player who lost a point, the player1.
     */
    public void resetLeft() {
        pause();
        puck.resetPosition();
        puck.setXspeed(-50f);
        resetPaddles();
        resume();
    }

    /**
     * Reset the board after player1 scored.
     * The puck moves towards the player who lost a point, the player2.
     */
    public void resetRight() {
        pause();
        puck.resetPosition();
        puck.setXspeed(50f);
        resetPaddles();
        resume();
    }

    /**
     * Reset the paddles on the board to the initial position.
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
        puckImage.dispose();
        stage.dispose();
    }
}
