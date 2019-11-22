package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    final MyGdxGame game;

    public Stage stage;
    public Texture myTexture;
    public TextureRegion myTextureRegion;
    public TextureRegionDrawable myTexRegionDrawable;
    public ImageButton button;
    public Label outputLabel;

    public LoginScreen(final MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        ImageButton button = createButton("login.png");
        ImageButton button2 = createButton("register.png");
        button.setPosition(300, 200);
        button2.setPosition(300, 100);
        game.font.setColor(Color.RED);
        stage.addActor(button);
        stage.addActor(button2);
        outputLabel = new Label("label ",new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        outputLabel.setText("Please log in before playing the game.");
        outputLabel.setPosition(200, 400);
        button.add(outputLabel).expand().fill();
        stage.addActor(outputLabel);
    }

    public ImageButton createButton(String path) {
        myTexture = new Texture(Gdx.files.internal(path));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        button.setHeight(100);
        button.setWidth(200);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.print("button pressed");
                        outputLabel.setText("pressed");
                    };
                });
        return button;
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

        //start here if the play button is pushed, we start a new game
        //add for play button later, initially start game on space bar press
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
