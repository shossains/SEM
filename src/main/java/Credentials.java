import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Credentials implements Screen {

    public transient MyGdxGame game;
    public transient Stage stage;
    private transient String username;
    private transient String password;
    private transient Image image;
    private transient Music sound;

    /**
     * Start the game with a skin.
     * @param game The game itself.
     */
    public Credentials(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("assets/uiSkin.json"));
        final TextField usernameTextField = new TextField("", skin);
        final TextField passwordTextField = new TextField("", skin);
        usernameTextField.setPosition(250,200);
        usernameTextField.setSize(300, 50);
        passwordTextField.setPosition(250, 100);
        passwordTextField.setSize(300, 50);
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);
        sound = Gdx.audio.newMusic(Gdx.files.internal("assets/test.ogg"));
        sound.setLooping(true);
        sound.play();
        image = new Image(new Texture("air3.png"));
        stage.addActor(image);
        TextButton button = new TextButton("Done!", skin);
        button.setPosition(100, 300);
        button.setSize(100, 50);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        username = usernameTextField.getText();
                        password = passwordTextField.getText();
                        System.out.print(username + " " + password);

                    }
                });
        stage.addActor(button);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)1, (float)204 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        game.font.setColor(Color.ROYAL);
        game.font.draw(game.spriteBatch, "username", 130, 225);
        game.font.draw(game.spriteBatch, "password", 130, 125);
        image.setSize(150, 150);
        image.setPosition(350, 300);
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
        game.spriteBatch.dispose();
    }
}
