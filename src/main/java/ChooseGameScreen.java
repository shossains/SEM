
import com.badlogic.gdx.Game;
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

    final transient MyGdxGame game;

    public transient Stage stage;
    public transient ImageButton backButton;
    public transient ImageButton localGameButton;
    public transient ImageButton vsAiGameButton;
    public transient ImageButton onlineGameButton;
    public transient Texture myTexture;
    public transient TextureRegion myTextureRegion;
    public transient TextureRegionDrawable myTexRegionDrawable;
    public transient Label outputLabel;


    /**
     * Constructor for this Screen.
     * @param game the current game instance
     */
    public ChooseGameScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backButton = createBackButton("assets/back.png");
        localGameButton = createLocalGameButton("assets/local.png");
        vsAiGameButton = createVsAiButton("assets/vsAI.png");
        onlineGameButton = createOnlineButton("assets/online.png");
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
        ImageButton locButton = createButton(path);
        locButton.setPosition(220, 300);
        locButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new
                                GameScreen(game));

                    }
                });
        stage.addActor(locButton);
        return locButton;
    }

    private ImageButton createVsAiButton(String path) {
        ImageButton vsButton = createButton(path);

        vsButton.setPosition(220, 230);
        stage.addActor(vsButton);
        return vsButton;
    }

    private ImageButton createOnlineButton(String path) {
        ImageButton onButton = createButton(path);

        onButton.setPosition(220, 160);
        stage.addActor(onButton);
        return onButton;
    }

    private ImageButton createBackButton(String path) {
        ImageButton bacButton = createButton(path);
        bacButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new MainMenuScreen(game));
                    }

                });
        bacButton.setPosition(220, 90);
        stage.addActor(bacButton);
        return bacButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
