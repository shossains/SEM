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
import database.Adapter;
import database.RegisterUser;
import gamelogic.CredentialsChecker;

/**
 * The purpose of this class is to create a graphical user interface
 * for the registration screen. This class makes use of the gui.ButtonFactory
 * and gui.TextFieldFactory classes for creating new objects and giving them
 * functionality.
 * Here, a user can create an new account if some conditions are satisfied.
 * These conditions are verified in the submitCredentials method.
 */
public class RegistrationScreen implements Screen {
    private transient AirHockeyGame game;
    public transient Stage stage;

    private transient String email;
    private transient String username;
    private transient String password;
    private transient String passwordAgain;
    private transient Image image;
    private transient TextFieldFactory textFieldFactory;
    private transient DialogFactory dialogFactory;

    final transient TextField usernameTextField;
    final transient TextField passwordTextField;
    final transient TextField emailTextField;
    final transient TextField passwordAgainTextField;

    private transient boolean mutePressed;

    /**
     * Constructor for registration screen.
     * Initialize every object used in this scene.
     * @param game the current game.
     */
    public RegistrationScreen(final AirHockeyGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        textFieldFactory = new TextFieldFactory(this.game, this);
        dialogFactory = new DialogFactory(this.game, this);

        usernameTextField = textFieldFactory.createTextField();
        passwordTextField = textFieldFactory.createTextField();
        emailTextField = textFieldFactory.createTextField();
        passwordAgainTextField = textFieldFactory.createTextField();
        usernameTextField.setPosition(500,250);
        passwordTextField.setPosition(500, 150);
        emailTextField.setPosition(500, 350);
        passwordAgainTextField.setPosition(500, 50);
        passwordAgainTextField.setPasswordMode(true);
        passwordAgainTextField.setPasswordCharacter('*');
        passwordTextField.setPasswordMode(true);
        passwordTextField.setPasswordCharacter('*');
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);
        stage.addActor(emailTextField);
        stage.addActor(passwordAgainTextField);
        image = new Image(new Texture("assets/air2.png"));
        stage.addActor(image);
        AbstractButtonFactory buttonFactory = new TextButtonFactory(this.game, this);
        Button button = buttonFactory.createButton("Done");
        button.setPosition(50, 500);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        submitCredentials();
                    }
                });
        stage.addActor(button);
        Button exit = buttonFactory.createTransButton("Back", "LoginScreen");
        exit.setPosition(900, 600);
        stage.addActor(exit);
    }

    /**
     * Method for verifying that an account can be created.
     * If the username or email are already registered in the database,
     * a pop-up will be shown and the user will be notified.
     * Otherwise, a new account is created.
     * This method makes use of the Query class for interactions with
     * the database.
     */
    private void submitCredentials() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();
        email = emailTextField.getText();
        passwordAgain = passwordAgainTextField.getText();

        Adapter adapter = new Adapter();
        RegisterUser registerUser = new RegisterUser(adapter.conn, username, password, email);
        CredentialsChecker credentialsChecker = new CredentialsChecker(this, adapter, registerUser);
        String result = credentialsChecker.checkRegisterCredentials(username, password,
                email, passwordAgain);

        switch (result) {
            case "empty": {
                Dialog dialog = dialogFactory.createDialog("Empty fields",
                        "Please fill in all fields!");
                dialog.show(stage);
                break;
            }

            case "passwordsNotMatching": {
                Dialog dialog = dialogFactory.createDialog("Warning - wrong password",
                        "Please enter the password again."
                        + " Your passwords do not match.");
                dialog.show(stage);
                break;
            }

            case "correct": {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new
                        MainMenuScreen(game));
                break;
            }

            case "incorrect": {
                Dialog dialog = dialogFactory.createDialog("Email or username already in use.",
                        "Please try again.");
                dialog.show(stage);
                break;
            }

            default: {
                Dialog dialog = dialogFactory.createDialog("Error.",
                        "Please try again.");
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

        Gdx.gl.glClearColor((float)1, (float)150 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        image.setSize(200, 200);
        image.setPosition(10, 100);
        game.font.setColor(Color.ROYAL);
        game.font.draw(game.spriteBatch, "Email:", 400, 382);
        game.font.draw(game.spriteBatch, "Username:", 400, 282);
        game.font.draw(game.spriteBatch, "Password:", 400, 182);
        game.font.draw(game.spriteBatch, "Password:", 400, 82);
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
       // game.spriteBatch.dispose();
    }
}