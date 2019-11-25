package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    public transient Label outputLabel;

    public LoginScreen(final MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        ImageButton loginButton = createLoginButton("login.png");
        ImageButton registerButton = createRegisterButton("register.png");
        loginButton.setPosition(300, 200);
        registerButton.setPosition(300, 100);
        game.font.setColor(Color.RED);
        stage.addActor(loginButton);
        stage.addActor(registerButton);
        outputLabel = new Label("label ",new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        outputLabel.setText("Please log in before playing the game.");
        outputLabel.setPosition(200, 400);
        stage.addActor(outputLabel);
    }

    public ImageButton createButton(String path) {
        myTexture = new Texture(Gdx.files.internal(path));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setHeight(100);
        button.setWidth(200);
        return button;
    }

    public ImageButton createLoginButton(String path) {
        ImageButton loginButton = createButton(path);
        loginButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.print("button pressed");
                        dispose();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new
                                Credentials(game));
                        outputLabel.setText("pressed");
                    }
                }
                );
        return loginButton;
    }

    public ImageButton createRegisterButton(String path) {
        ImageButton registerButton = createButton(path);
        registerButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.print("button pressed");
                        outputLabel.setText("pressed");
                    }
                }
                );
        return registerButton;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)204 / 255, (float)204 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.spriteBatch.begin();
        stage.act();
        stage.draw();
        game.spriteBatch.end();
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
