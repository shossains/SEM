package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Registration implements Screen {
    private transient MyGdxGame game;
    public transient Stage stage;
    private transient String email;
    private transient String username;
    private transient String password;
    private transient String passwordAgain;
    private transient Image image;

    /**
     * Constructor for registration screen.
     * Initialize every object used in this scene.
     * @param game the current game.
     */
    public Registration(final MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("ostrich-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 26;
        this.game.font = generator.generateFont(parameter);
        generator.dispose();
        final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextField usernameTextField = new TextField("", skin);
        final TextField passwordTextField = new TextField("", skin);
        final TextField emailTextField = new TextField("", skin);
        final TextField passwordAgainTextField = new TextField("", skin);
        usernameTextField.setPosition(300,250);
        usernameTextField.setSize(300, 50);
        passwordTextField.setPosition(300, 150);
        passwordTextField.setSize(300, 50);
        emailTextField.setPosition(300, 350);
        emailTextField.setSize(300, 50);
        passwordAgainTextField.setPosition(300, 50);
        passwordAgainTextField.setSize(300, 50);
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);
        stage.addActor(emailTextField);
        stage.addActor(passwordAgainTextField);
        image = new Image(new Texture("air2.png"));
        stage.addActor(image);
        TextButton button = new TextButton("Done", skin);
        button.setColor(Color.ROYAL);
        button.setPosition(20, 400);
        button.setSize(100, 50);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        username = usernameTextField.getText();
                        password = passwordTextField.getText();
                        email = emailTextField.getText();
                        passwordAgain = passwordAgainTextField.getText();
                        if (!passwordAgain.equals(password)) {
                            Dialog dialog = new Dialog("Warning - wrong password", skin, "dialog") {
                                public void result(Object obj) {
                                    System.out.println("result " + obj);
                                }
                            };
                            dialog.setColor(Color.ROYAL);
                            dialog.setSize(400, 200);
                            dialog.text("Please enter the password again."
                                    +
                                    " Your passwords do not match.");
                            dialog.button("Ok", false);
                            dialog.show(stage);
                        }
                    }
                });
        stage.addActor(button);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)1, (float)150 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        image.setSize(200, 200);
        image.setPosition(10, 100);
        game.font.setColor(Color.ROYAL);
        game.font.draw(game.spriteBatch, "Email:", 200, 382);
        game.font.draw(game.spriteBatch, "Username:", 200, 282);
        game.font.draw(game.spriteBatch, "Password:", 200, 182);
        game.font.draw(game.spriteBatch, "Password:", 200, 82);
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