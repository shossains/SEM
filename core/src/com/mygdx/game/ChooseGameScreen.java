package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ChooseGameScreen implements Screen {

    final MyGdxGame game;

    public Stage stage;
    public ImageButton backButton;
    public ImageButton localGameButton;
    public ImageButton vsAIGameButton;
    public ImageButton onlineGameButton;
    public Texture myTexture;
    public TextureRegion myTextureRegion;
    public TextureRegionDrawable myTexRegionDrawable;
    public Label outputLabel;

    public ChooseGameScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backButton = createBackButton("back.png");
        localGameButton = createLocalGameButton("local.png");
        vsAIGameButton = createVsAIButton("vsAI.png");
        onlineGameButton = createOnlineButton("online.png");
        game.font.setColor(Color.RED);
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

    private ImageButton createLocalGameButton(String path) {
        ImageButton lButton = createButton(path);

        lButton.setPosition(220, 300);
        stage.addActor(lButton);
        return lButton;
    }

    private ImageButton createVsAIButton(String path) {
        ImageButton vButton = createButton(path);

        vButton.setPosition(220, 230);
        stage.addActor(vButton);
        return vButton;
    }

    private ImageButton createOnlineButton(String path) {
        ImageButton oButton = createButton(path);

        oButton.setPosition(220, 160);
        stage.addActor(oButton);
        return oButton;
    }

    private ImageButton createBackButton(String path) {
        ImageButton bButton = createButton(path);
        bButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new MainMenuScreen(game));
                    };

                });
        bButton.setPosition(220, 90);
        stage.addActor(bButton);
        return bButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)204/255, (float)204/255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        game.font.getData().setScale(1.6f);
        game.font.draw(game.spriteBatch, "Choose game mode", 235, 450);
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
