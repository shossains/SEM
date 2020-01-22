package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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

    private transient TextField usernameTextField;
    private transient TextField passwordTextField;
    private transient TextField emailTextField;
    private transient TextField passwordAgainTextField;

    /**
     * Constructor for registration screen.
     * Initialize every object used in this scene.
     * @param game the current game.
     */
    public RegistrationScreen(final AirHockeyGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        dialogFactory = new DialogFactory(this.game, this);

        createFields();
        createButtons();
        image = new Image(new Texture("assets/air2.png"));
        stage.addActor(image);

    }

    /**
     * Method for creating buttons in constructor.
     */
    private void createButtons() {
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
     * Method for creating fields in constructor.
     */
    private void createFields() {
        textFieldFactory = new TextFieldFactory(this.game, this);

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
        SubmitCredentials submitCredentials = new SubmitAuthenticationCredentials();
        submitCredentials.submitCredentials(game, this, username, password, email, passwordAgain);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        boolean mutePressed = Gdx.input.isKeyJustPressed(Input.Keys.M);
        if (mutePressed) {
            game.muteUnmute();
        }

        boolean enterPressed = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
        if (enterPressed) {
            submitCredentials();
        }

        draw();
    }

    /**
     * Method called in render in order to implement draw logic.
     */
    private void draw() {
        Gdx.gl.glClearColor((float)1, (float)150 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        drawSpriteBatch();
    }

    /**
     * Method that handles spriteBatch logic
     */
    private void drawSpriteBatch() {
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