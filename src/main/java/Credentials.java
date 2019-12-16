import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import database.Query;

@SuppressWarnings({"PMD.CloseResource", "CustomImportOrder"})
public class Credentials implements Screen {

    public transient MyGdxGame game;
    public transient Stage stage;
    private transient String username;
    private transient String password;
    private transient Image image;

    final transient TextFieldFactory textFieldFactory;

    private transient boolean mutePressed;
    final transient TextField usernameTextField;
    final transient TextField passwordTextField;

    /**
     * Start the game with a skin.
     * @param game The game itself.
     */
    public Credentials(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        textFieldFactory = new TextFieldFactory(this.game, this);
        usernameTextField = textFieldFactory.createTextField();
        passwordTextField = textFieldFactory.createTextField();
        usernameTextField.setPosition(250,200);
        passwordTextField.setPosition(250, 100);
        passwordTextField.setPasswordMode(true);
        passwordTextField.setPasswordCharacter('*');
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);
        image = new Image(new Texture("assets/air3.png"));
        stage.addActor(image);
        ButtonFactory factory = new ButtonFactory(this.game, this);
        TextButton exit = factory.createTransTextButton("Exit!", "LoginScreen");
        exit.setPosition(900, 600);
        TextButton button = factory.createTextButton("Done!");
        button.setPosition(100, 300);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        submitCredentials();
                    }
                });
        stage.addActor(button);
        stage.addActor(exit);
    }

    private void submitCredentials() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();

        if (username.equals("") || password.equals("")) {
            Dialog dialog = new Dialog("Empty fields",
                    textFieldFactory.createSkin(),
                    "dialog") {
            };
            dialog.setColor(Color.RED);
            dialog.setSize(400, 200);
            dialog.text("Please fill in all fields!");
            dialog.button("Ok", false);
            dialog.show(stage);
        } else {
            if (Query.verifyLogin(username, password)) {
                MainMenuScreen m = new MainMenuScreen(game);
                m.username = username;
                ((Game) Gdx.app.getApplicationListener()).setScreen(m);
            } else {
                Dialog dialog = new Dialog("Incorrect credentials",
                        textFieldFactory.createSkin(),
                        "dialog") {
                };
                dialog.setColor(Color.RED);
                dialog.setSize(400, 200);
                dialog.text("Incorrect user and/or password");
                dialog.button("Ok", false);
                dialog.show(stage);
            }
        }
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

        boolean enterPressed = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (enterPressed) {
            submitCredentials();
        }

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
        //game.spriteBatch.dispose();
    }
}
