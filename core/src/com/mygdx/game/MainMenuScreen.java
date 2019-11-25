package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    final MyGdxGame game;

    public Stage stage;
    public ImageButton playButton;
    public ImageButton settingsButton;
    public ImageButton logoutButton;
    public ImageButton exitButton;
    public Texture myTexture;
    public TextureRegion myTextureRegion;
    public TextureRegionDrawable myTexRegionDrawable;
    public Label outputLabel;

    public MainMenuScreen(MyGdxGame game) {

        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        playButton = createPlayButton("play.png");

        settingsButton = createSettingsButton("settings.png");

        logoutButton = createLogoutButton("logout.png");

        exitButton = createExitButton("exit.png");

        game.font.setColor(Color.RED);

        outputLabel = new Label("label ",new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        outputLabel.setText("Please log in before playing the game.");
        outputLabel.setPosition(200, 400);
        playButton.add(outputLabel).expand().fill();
        stage.addActor(outputLabel);
    }



    private ImageButton createButton(String path) {
        myTexture = new Texture(Gdx.files.internal(path));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setHeight(100);
        button.setWidth(200);
        return button;
    }



    private ImageButton createPlayButton(String path) {
        ImageButton pButton = createButton(path);
        pButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new ChooseGameScreen(game));
                    };

                });
        pButton.setPosition(280, 320);
        stage.addActor(pButton);
        return pButton;
    }

    private ImageButton createSettingsButton(String path) {
        ImageButton sButton = createButton(path);
        sButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new SettingsScreen(game));
                    };

                });
        sButton.setPosition(230, 250);
        stage.addActor(sButton);
        return sButton;
    }

    private ImageButton createLogoutButton(String path) {
        ImageButton loButton = createButton(path);
        loButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new LoginScreen(game));
                    };

                });
        loButton.setPosition(230, 180);
        stage.addActor(loButton);

        return loButton;
    }

    private ImageButton createExitButton(String path) {
        ImageButton eButton = createButton(path);
        eButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        Gdx.app.exit();
                        System.exit(0);
                    };

                });
        eButton.setPosition(230, 110);
        stage.addActor(eButton);

        return eButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)204/255, (float)204/255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.spriteBatch.begin();
        stage.act();
        game.font.draw(game.spriteBatch, "Hello", 200, 200);
        stage.draw();
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
