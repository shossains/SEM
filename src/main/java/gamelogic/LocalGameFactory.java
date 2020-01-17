package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import gui.GameScreen;
import scoring.*;

import java.util.ArrayList;

public class LocalGameFactory extends GameFactory {

    private static final float WIDTH = 1280;
    private static final float HEIGHT = 720;

    private static final float BASIC_GOAL_DEPTH = 15;

    private static final float PADDLE_MAX_SPEED = 300;
    private static final float PADDLE_LOW_SPEED = 75;
    private static final float PADDLE_ACCELERATION = 10;
    public static final float PADDLE_PUCK_E = 0.8f;
    public static final float PUCK_WALL_E = 0.85f;

    @Override
    public LocalGameContainer createGameContainer(Sound scoreSound, Sound collisionSound,
                                                  GameScreen screen, Hud hud) {
        Texture boardImage = new Texture(Gdx.files.internal("assets/board.png"));
        Texture goalOneImage = new Texture(Gdx.files.internal("assets/leftGoal.png"));
        Texture goalTwoImage = new Texture(Gdx.files.internal("assets/rightGoal.png"));
        Texture puckImage = new Texture(Gdx.files.internal("assets/puck.png"));
        Texture paddle1Image = new Texture(Gdx.files.internal("assets/redPaddle.png"));
        Texture paddle2Image = new Texture(Gdx.files.internal("assets/bluePaddle.png"));

        ArrayList<Texture> textures = new ArrayList<>();

        textures.add(boardImage);
        textures.add(goalOneImage);
        textures.add(goalTwoImage);
        textures.add(puckImage);
        textures.add(paddle1Image);
        textures.add(paddle2Image);

        // Create the scoring system
        BasicScoringSystem basicScoringSystem = new BasicScoringSystem(hud, screen, scoreSound);

        // Create the goals
        Goal goalOne = new Goal((HEIGHT / 3), 2 * (HEIGHT / 3),
                BASIC_GOAL_DEPTH, basicScoringSystem, PlayerType.PLAYER1);
        Goal goalTwo = new Goal((HEIGHT / 3), 2 * (HEIGHT / 3),
                (WIDTH - BASIC_GOAL_DEPTH), basicScoringSystem, PlayerType.PLAYER2);

        // Create the board
        Board board = new Board(0, 0, WIDTH, HEIGHT, goalOne, goalTwo);

        //we should later change it to the resolution and so on...

        Puck puck = new Puck(640f, 360f, 30f, 0f, 30f,
                5, WIDTH, HEIGHT);

        Paddle paddle1 = new Paddle(1000f, 360f, 0f, 0f, 40f, 10, WIDTH, HEIGHT,
                PlayerType.PLAYER1, PADDLE_MAX_SPEED, PADDLE_ACCELERATION, PADDLE_LOW_SPEED);
        Paddle paddle2 = new Paddle(360, 360f, 0f, 0f, 40f, 10, WIDTH, HEIGHT,
                PlayerType.PLAYER2, PADDLE_MAX_SPEED, PADDLE_ACCELERATION, PADDLE_LOW_SPEED);
        ArrayList<Entity> entities = new ArrayList<>();

        entities.add(board);
        entities.add(goalOne);
        entities.add(goalTwo);
        entities.add(puck);
        entities.add(paddle1);
        entities.add(paddle2);

        CollisionsEngine collisionsEngine = new CollisionsEngine(PADDLE_PUCK_E, PUCK_WALL_E, collisionSound);

        LocalGameContainer gameContainer = new LocalGameContainer(entities, textures,
                basicScoringSystem, collisionsEngine);

        return gameContainer;
    }
}
