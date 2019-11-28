
import GameLogic.CollisionsEngine;
import GameLogic.Paddle;
import GameLogic.Puck;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {
    final transient MyGdxGame game;

    transient Texture puckImage;
    transient Texture paddle1Image;
    transient Texture paddle2Image;
    transient Texture boardImage;

    transient Board board;
    transient Puck puck;
    transient Paddle paddle1, paddle2;

    transient CollisionsEngine collisionsEngine;

    transient OrthographicCamera camera;

    transient boolean initMove = true;

    public GameScreen(final MyGdxGame game) {
        this.game = game;

        boardImage = new Texture(Gdx.files.internal("assets/table.png"));
        puckImage = new Texture(Gdx.files.internal("assets/hockey-puck.png"));
        paddle1Image = new Texture(Gdx.files.internal("assets/redPaddle.png"));
        paddle2Image = new Texture(Gdx.files.internal("assets/bluePaddle.png"));

        camera = new OrthographicCamera();
        //we can change the resolution to whatever is appropriate later
        camera.setToOrtho(false, 1280, 720);

        // Create the board
        board = new Board(0, 0, 1280, 720);

        //we should later change it to the resolution and so on...
        puck = new Puck(640f, 360f, 30f, 0f, 30f);

        paddle1 = new Paddle(360f, 360f, 0f, 0f, 40f);
        paddle2 = new Paddle(1000f, 360f, 0f, 0f, 40f);

        collisionsEngine = new CollisionsEngine(puck, paddle1, paddle2);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //background colour
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update the camera
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.spriteBatch.setProjectionMatrix(camera.combined);

        game.spriteBatch.begin();

        // Draw the board
        game.spriteBatch.draw(boardImage, board.x, board.y, board.width, board.height);

        //draw the puck as the texture and in the place that the puck exists
        //Maybe there is some border, or the radius doesn't perfectly scale up the image
        //the boundary is still not totally correct
        game.spriteBatch.draw(puckImage, puck.x - puck.radius, puck.y - puck.radius,
                puck.radius*2, puck.radius*2);

        game.spriteBatch.draw(paddle1Image, paddle1.x - paddle1.radius, paddle1.y - paddle1.radius,
                paddle1.radius*2, paddle1.radius*2);

        game.spriteBatch.draw(paddle2Image, paddle2.x - paddle2.radius, paddle2.y - paddle2.radius,
                paddle2.radius*2, paddle2.radius*2);

        game.spriteBatch.end();

        //move the puck

        float deltaTime = Gdx.graphics.getDeltaTime();

        puck.movePuck(deltaTime);
        //ensure it is within boundaries
        puck.fixPosition();

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

        paddle1.movePaddle(deltaTime);
        paddle2.movePaddle(deltaTime);

        collisionsEngine.Collide();

        paddle1.fixPosition();
        paddle2.fixPosition();
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
        puckImage.dispose();

    }
}
