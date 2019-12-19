package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ChooseGameScreen implements Screen {

    final transient MyGdxGame game;

    public transient Stage stage;
    public transient ImageButton backButton;
    public transient ImageButton localGameButton;
    public transient ImageButton vsAiGameButton;
    public transient ImageButton onlineGameButton;
    public transient ButtonFactory buttonFactory;
    public transient Label outputLabel;

    private transient boolean mutePressed;


    /**
     * Constructor for this Screen.
     * @param game the current game instance
     */
    public ChooseGameScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.buttonFactory = new ButtonFactory(game, this);

        localGameButton = buttonFactory.createTransImButton("assets/local.png", "GameScreen");
        vsAiGameButton = buttonFactory.createImButton("assets/vsAI.png");
        onlineGameButton = buttonFactory.createImButton("assets/online.png");
        backButton = buttonFactory.createTransImButton("assets/back.png", "MainMenuScreen");

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
        mutePressed = Gdx.input.isKeyJustPressed(Input.Keys.M);
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
