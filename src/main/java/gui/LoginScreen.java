package gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import gamelogic.CredentialsChecker;

import java.sql.SQLException;

/**
 * The meaning of this class is to create an graphical user interface
 * for loggin in. If the user already has an account, he/she can enter
 * the username and password. If the data is correct, the user will be redirected
 * to the MainMenuScreen.
 */
public class LoginScreen implements Screen {

    public transient AirHockeyGame game;
    public transient Stage stage;
    private transient String username;
    private transient String password;
    private transient Image image;

    final transient TextFieldFactory textFieldFactory;
    private transient AbstractButtonFactory buttonFactory;

    private transient boolean mutePressed;
    final transient TextField usernameTextField;
    final transient TextField passwordTextField;

    private transient DialogFactory dialogFactory;

    /**
     * Constructor for login screen.
     * Here, using the gui.TextFieldFactory and gui.ButtonFactory classes,
     * new objects are created.
     * The user is able to enter his/her username and password, through the
     * text fields created below. When done, the player can either press
     * the 'Done' button or Enter button to submit credentials.
     * @param game The game itself.
     */
    public LoginScreen(AirHockeyGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.dialogFactory = new DialogFactory(game, this);

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

        buttonFactory = new TextButtonFactory(this.game, this);
        Button exit = buttonFactory.createTransButton("Exit!", "LoginScreen");
        exit.setPosition(900, 600);
        Button button = buttonFactory.createButton("Done!");
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

    /**
     * Method that checks if a user already has an account.
     * Given an username and a password, this method
     * makes use of the Query class and checks whether the
     * account exists in the User's database.
     * If not, a pop-up is shown to the user with label "incorrect credentials".
     * If the fields are empty and the user is done, another pop-up will state this.
     */
    private void submitCredentials() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();

        CredentialsChecker credentialsChecker = new CredentialsChecker(this);
        String response = credentialsChecker.checkLoginCredentials(username, password);
        switch (response) {
            case "empty" : {
                Dialog dialog = dialogFactory.createDialog("Empty fields",
                        "Please fill in all fields!");
                dialog.show(stage);
                break;
            }

            case "correct": {
                MainMenuScreen m = new MainMenuScreen(game);
                m.username = username;
                ((Game) Gdx.app.getApplicationListener()).setScreen(m);
                break;
            }

            case "incorrect": {
                Dialog dialog = dialogFactory.createDialog("Incorrect credentials",
                        "Incorrect username and/or password");
                dialog.show(stage);
                break;
            }
            default: {
                Dialog dialog = dialogFactory.createDialog("Error",
                        "Please try again");
                dialog.show(stage);
                break;
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
