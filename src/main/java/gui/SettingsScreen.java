package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This is a graphical user interface for the Settings
 * Screen. In a later development, the user will be able
 * to change his username.
 */
public class SettingsScreen implements Screen {

    final transient AirHockeyGame game;

    public transient Stage stage;
    public transient Button backButton;
    public transient AbstractButtonFactory abstractButtonFactory;


    /**
     * Constructor for this Screen.
     * @param game the current game instance
     */
    public SettingsScreen(AirHockeyGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        abstractButtonFactory = new ImageButtonFactory(game, this);
        backButton = abstractButtonFactory.createTransButton("assets/back.png", "MainMenuScreen");
        backButton.setPosition(220, 100);
        stage.addActor(backButton);

        game.font.setColor(Color.RED);

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

        Gdx.gl.glClearColor((float)204 / 255, (float)204 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        game.font.getData().setScale(1.6f);
        game.font.draw(game.spriteBatch, "Settings", 235, 450);
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
