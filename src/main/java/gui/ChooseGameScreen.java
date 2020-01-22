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
 * ChooseGameScreen is an graphical user interface where the user
 * can choose the game mode (local game, vs computer or online).
 */
public class ChooseGameScreen implements Screen {

    final transient AirHockeyGame game;

    public transient Stage stage;
    public transient Button backButton;
    public transient Button localGameButton;
    public transient Button vsAiGameButton;
    public transient Button onlineGameButton;
    public transient AbstractButtonFactory abstractButtonFactory;

    /**
     * Constructor for this Screen.
     * Here, the most important objects that are created are
     * the options for the game mode: 3 buttons, each one corresponding
     * to a game mode (local, online, vs computer).
     * @param game the current game instance
     */
    public ChooseGameScreen(AirHockeyGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.abstractButtonFactory = new ImageButtonFactory(game, this);

        localGameButton = abstractButtonFactory.createTransButton("assets/local.png", "GameScreen");
        vsAiGameButton = abstractButtonFactory.createButton("assets/vsAI.png");
        onlineGameButton = abstractButtonFactory.createButton("assets/online.png");
        backButton = abstractButtonFactory.createTransButton("assets/back.png", "MainMenuScreen");

        localGameButton.setPosition(220, 300);
        vsAiGameButton.setPosition(220, 230);
        onlineGameButton.setPosition(220, 160);
        backButton.setPosition(220, 90);

        stage.addActor(localGameButton);
        stage.addActor(vsAiGameButton);
        stage.addActor(onlineGameButton);
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
