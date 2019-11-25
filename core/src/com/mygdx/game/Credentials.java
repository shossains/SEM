package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    public Credentials(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextField usernameTextField = new TextField("", skin);
        final TextField passwordTextField = new TextField("", skin);
        usernameTextField.setPosition(250,200);
        usernameTextField.setSize(300, 50);
        passwordTextField.setPosition(250, 100);
        passwordTextField.setSize(300, 50);
        stage.addActor(usernameTextField);
        stage.addActor(passwordTextField);


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
        stage.dispose();
    }
}
