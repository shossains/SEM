package gui;

import com.badlogic.gdx.Game;
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
import gamelogic.Paddle;
import gamelogic.PlayerType;
import gamelogic.Puck;
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

        collisionsEngine = new CollisionsEngine(puck, paddle1, paddle2, paddlePucke);
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

        puck.update(delta);

        paddle1.update(delta);
        paddle2.update(delta);

        collisionsEngine.collide();

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

        //the movement variables for player 1
        boolean rightPressed1 = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean leftPressed1 = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean upPressed1 = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed1 = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        //the movement variables for player 2
        boolean rightPressed2 = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean leftPressed2 = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean upPressed2 = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean downPressed2 = Gdx.input.isKeyPressed(Input.Keys.S);

        paddle1.setSpeeds(rightPressed1, leftPressed1, upPressed1, downPressed1);
        paddle2.setSpeeds(rightPressed2, leftPressed2, upPressed2, downPressed2);

        paddle1.move(delta);
        paddle2.move(delta);

        collisionsEngine.collide();

        paddle1.fixPosition();
        paddle2.fixPosition();
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

        // Draw the board
        //game.spriteBatch.draw(boardImage, board.x, board.y, board.width, board.height);
        board.render(game, boardImage);

        puck.render(game, puckImage);

        paddle1.render(game, paddle1Image);
        paddle2.render(game, paddle2Image);

        /*
        //draw the puck as the texture and in the place that the puck exists
        //Maybe there is some border, or the radius doesn't perfectly scale up the image
        //the boundary is still not totally correct
        game.spriteBatch.draw(puckImage, puck.x - puck.radius, puck.y - puck.radius,
                puck.radius * 2, puck.radius * 2);

        game.spriteBatch.draw(paddle1Image, paddle1.x - paddle1.radius, paddle1.y - paddle1.radius,
                paddle1.radius * 2, paddle1.radius * 2);

        game.spriteBatch.draw(paddle2Image, paddle2.x - paddle2.radius, paddle2.y - paddle2.radius,
                paddle2.radius * 2, paddle2.radius * 2);


         */

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
