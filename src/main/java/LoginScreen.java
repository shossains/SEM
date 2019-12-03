import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginScreen implements Screen {

    final transient MyGdxGame game;
    public transient Stage stage;
    public transient Texture myTexture;
    public transient TextureRegion myTextureRegion;
    public transient TextureRegionDrawable myTexRegionDrawable;
    public transient ImageButton button;
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
        ImageButton loginButton = createLoginButton("assets/login.png");
        ImageButton registerButton = createRegisterButton("assets/register.png");
        ImageButton exitButton = createImageButton("assets/exit2.png");
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

    /**
     * Method for creating a new exit button.
     * @param path of the image used.
     * @return a new exit button.
     */
    private ImageButton createImageButton(String path) {
        ImageButton exitButton = createButton(path);
        exitButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        Gdx.app.exit();
                    }
                }
        );
        return exitButton;
    }

    /**
     * Helper method for creating a button.
     * @param path of the image used for texture.
     * @return a new button.
     */
    public ImageButton createButton(String path) {
        myTexture = new Texture(Gdx.files.internal(path));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setHeight(100);
        button.setWidth(200);
        return button;
    }

    /**
     * Method that creates a login button.
     * @param path of image used for the texture.
     * @return new created button.
     */
    public ImageButton createLoginButton(String path) {
        ImageButton loginButton = createButton(path);
        loginButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new
                                Credentials(game));
                    }
                }
        );
        return loginButton;
    }

    /**
     * Method for creating a register button.
     * @param path of the image for the texture.
     * @return a new register button.
     */
    public ImageButton createRegisterButton(String path) {
        ImageButton registerButton = createButton(path);
        registerButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new
                                Registration(game));
                    }
                }
        );
        return registerButton;
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
