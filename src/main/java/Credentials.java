import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;


@SuppressWarnings({"PMD.CloseResource", "CustomImportOrder"})
public class Credentials implements Screen {

    public transient MyGdxGame game;
    public transient Stage stage;
    private transient String username;
    private transient String password;
    private transient Image image;

    private transient boolean mutePressed;

    /**
     * Start the game with a skin.
     * @param game The game itself.
     */
    public Credentials(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        AssetManager assetManager = new AssetManager();
        assetManager.load("assets/uiskin.json", Skin.class);
        assetManager.finishLoading();
        final String skinPath = "assets/uiskin.json";
        Skin skin = new Skin(Gdx.files.internal(skinPath));
        final TextField usernameTextField = new TextField("", skin);
        final TextField passwordTextField = new TextField("", skin);
        usernameTextField.setPosition(250,200);
        usernameTextField.setSize(300, 50);
        passwordTextField.setPosition(250, 100);
        passwordTextField.setSize(300, 50);
        passwordTextField.setPasswordMode(true);
        passwordTextField.setPasswordCharacter('*');
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);
        image = new Image(new Texture("assets/air3.png"));
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

                        if (username.equals("") || password.equals("")) {
                            Dialog dialog = new Dialog("Empty fields",
                                    assetManager.get(skinPath, Skin.class),
                                    "dialog") {
                            };
                            dialog.setColor(Color.RED);
                            dialog.setSize(400, 200);
                            dialog.text("Please fill in all fields!");
                            dialog.button("Ok", false);
                            dialog.show(stage);
                        } else {
                            try {
                                if (checkCred(username, password)) {
                                    MainMenuScreen m = new MainMenuScreen(game);
                                    m.username = username;
                                    ((Game) Gdx.app.getApplicationListener()).setScreen(m);
                                } else {
                                    Dialog dialog = new Dialog("Incorrect credentials",
                                            assetManager.get(skinPath, Skin.class),
                                            "dialog") {
                                    };
                                    dialog.setColor(Color.RED);
                                    dialog.setSize(400, 200);
                                    dialog.text("Incorrect user and/or password");
                                    dialog.button("Ok", false);
                                    dialog.show(stage);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        stage.addActor(button);
        TextButton exit = new TextButton("Back", skin);
        exit.setPosition(900, 600);
        exit.setSize(100, 50);
        exit.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new
                                LoginScreen(game));

                    }
                });
        stage.addActor(exit);
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

    private boolean checkCred(String user, String pass) throws SQLException {
        String[] queries = {"SELECT username,"
                + " password FROM users WHERE username = '" + user + "';"};
        ResultSet rs = Query.runQueries(queries)[0];
        try {
            while (rs.next()) {
                String usern = rs.getString(1);
                String passw = rs.getString(2);

                if (usern.equals(user) && BCrypt.checkpw(pass, passw)) {
                    rs.close();
                    return true;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs.close();
        return false;
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
