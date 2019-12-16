import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * The purpose of this class is to create an graphical user interface
 * which gives the possibility of logging in or registering to a user.
 * It makes use of the ButtonFactory class for creating new objects and giving
 * them functionality.
 */
public class LoginScreen implements Screen {

    final transient MyGdxGame game;
    public transient Stage stage;
    public transient Image image;
    private transient boolean mutePressed;

    /**
     * Constructor for the login page.
     * Every object from this page is created below.
     * @param game the current game.
     */
    public LoginScreen(final MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        ButtonFactory buttonFactory = new ButtonFactory(this.game, this);
        ImageButton loginButton = buttonFactory.createTransImButton("assets/login.png",
                "Credentials");
        ImageButton registerButton = buttonFactory.createTransImButton("assets/register.png",
                "Registration");
        ImageButton exitButton = buttonFactory.createTransImButton("assets/exit2.png", "Exit");
        exitButton.setPosition(1130, 640);
        loginButton.setPosition(500, 200);
        registerButton.setPosition(500, 100);
        game.font.setColor(Color.RED);
        stage.addActor(loginButton);
        stage.addActor(exitButton);
        stage.addActor(registerButton);
        image = new Image(new Texture("assets/air.png"));
        stage.addActor(image);
    }

    @Override
    public void render(float delta) {
        mutePressed = Gdx.input.isKeyJustPressed(Input.Keys.M);
        if (mutePressed) {
            game.muteUnmute();
        }

        Gdx.gl.glClearColor((float)204 / 255, (float)204 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.spriteBatch.begin();
        stage.act();
        stage.draw();
        game.spriteBatch.end();
        image.setPosition(5, 300);
        image.setSize(300, 300);
        //start here if the play button is pushed, we start a new game
        //add for play button later, initially start game on space bar press
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

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
        stage.dispose();
    }

}
