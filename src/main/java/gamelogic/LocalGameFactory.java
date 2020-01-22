package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import gui.GameScreen;

import gui.Hud;
import java.util.ArrayList;

import scoring.BasicScoringSystem;

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

        ArrayList<Texture> textures = createTexturesList();

        // Create the scoring system
        BasicScoringSystem basicScoringSystem = new BasicScoringSystem(hud, screen, scoreSound);

        ArrayList<Entity> entities = createEntitiesList(basicScoringSystem);

        CollisionsEngine collisionsEngine = new CollisionsEngine(PADDLE_PUCK_E,
                PUCK_WALL_E, collisionSound);

        return new LocalGameContainer(entities, textures, basicScoringSystem, collisionsEngine);

    }

    /**
     * Method to return a list of entities. These are the objects that are rendered
     * and interact with each other when the game is running.
     * @param basicScoringSystem ScoringSystem
     * @return List of entities.
     */
    public ArrayList<Entity> createEntitiesList(BasicScoringSystem basicScoringSystem) {
        // Create the goals
        Goal goalOne = new Goal((HEIGHT / 3), 2 * (HEIGHT / 3),
                BASIC_GOAL_DEPTH, basicScoringSystem, PlayerType.PLAYER1);
        Goal goalTwo = new Goal((HEIGHT / 3), 2 * (HEIGHT / 3),
                (WIDTH - BASIC_GOAL_DEPTH), basicScoringSystem, PlayerType.PLAYER2);

        // Create the board
        Board board = new Board(0, 0, WIDTH, HEIGHT, goalOne, goalTwo);

        //we should later change it to the resolution and so on...

        Puck puck = new Puck.PuckBuilder()
                .atXCoordinate(640f)
                .atYCoordinate(360f)
                .withSpeedX(30f)
                .withSpeedY(0f)
                .withRadius(30f)
                .withMass(5)
                .onWidth(WIDTH)
                .onHeight(HEIGHT)
                .build();

        Paddle paddle1 = new Paddle.PaddleBuilder()
                .atXCoordinate(1000f)
                .atYCoordinate(360f)
                .withSpeedX(0f)
                .withSpeedY(0f)
                .withRadius(40f)
                .withMass(10)
                .onWidth(WIDTH)
                .onHeight(HEIGHT)
                .withPlayerType(PlayerType.PLAYER1)
                .withMaxSpeed(PADDLE_MAX_SPEED)
                .withAcceleration(PADDLE_ACCELERATION)
                .withLowSpeed(PADDLE_LOW_SPEED)
                .build();

        Paddle paddle2 = new Paddle.PaddleBuilder()
                .atXCoordinate(360f)
                .atYCoordinate(360f)
                .withSpeedX(0f)
                .withSpeedY(0f)
                .withRadius(40f)
                .withMass(10)
                .onWidth(WIDTH)
                .onHeight(HEIGHT)
                .withPlayerType(PlayerType.PLAYER2)
                .withMaxSpeed(PADDLE_MAX_SPEED)
                .withAcceleration(PADDLE_ACCELERATION)
                .withLowSpeed(PADDLE_LOW_SPEED)
                .build();

        ArrayList<Entity> entities = new ArrayList<>();

        entities.add(board);
        entities.add(goalOne);
        entities.add(goalTwo);
        entities.add(puck);
        entities.add(paddle1);
        entities.add(paddle2);

        return entities;
    }

    /**
     * Method to create the list of textures for rendering the objects.
     * @return list of textures.
     */
    public ArrayList<Texture> createTexturesList() {
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

        return textures;
    }
}
