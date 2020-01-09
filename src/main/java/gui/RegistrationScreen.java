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
import database.Query;

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
        AbstractButtonFactory factory = new TextButtonFactory(this.game, this);
        Button button = factory.createButton("Done");
        button.setPosition(50, 500);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        submitCredentials();
                    }
                });
        stage.addActor(button);
        Button exit = factory.createTransButton("Back", "LoginScreen");
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
        if (username.equals("") || password.equals("")
                || email.equals("") || passwordAgain.equals("")) {
            Dialog dialoga = new Dialog("Empty fields",
                    textFieldFactory.createSkin(),
                    "dialog") {
                public void result(Object obj) {
                    System.out.println("result " + obj);
                }
            };
            dialoga.setColor(Color.RED);
            dialoga.setSize(400, 200);
            dialoga.text("Please fill in all fields.");
            dialoga.button("Ok", false);
            dialoga.show(stage);
        } else if  (!passwordAgain.equals(password)) {
            Dialog dialog = new Dialog("Warning - wrong password",
                    textFieldFactory.createSkin(), "dialog") {
            };
            dialog.setColor(Color.ROYAL);
            dialog.setSize(400, 200);
            dialog.text("Please enter the password again."
                    + " Your passwords do not match.");
            dialog.button("Ok", false);
            dialog.show(stage);
        } else {
            dispose();
            if (Query.addNewUser(username, password, email)) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new
                        MainMenuScreen(game));
            } else {
                Dialog dialog = new Dialog("Email or username already in use.",
                        textFieldFactory.createSkin(), "dialog") {
                };
                dialog.setColor(Color.ROYAL);
                dialog.setSize(400, 200);
                dialog.text("Please try again.");
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