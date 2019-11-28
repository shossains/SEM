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

public class MainMenuScreen implements Screen {

    final transient MyGdxGame game;

    public transient Stage stage;
    public transient ImageButton playButton;
    public transient ImageButton settingsButton;
    public transient ImageButton logoutButton;
    public transient ImageButton exitButton;
    public transient Texture myTexture;
    public transient TextureRegion myTextureRegion;
    public transient TextureRegionDrawable myTexRegionDrawable;
    public transient Label outputLabel;


    /**
     * Constructor for this Screen.
     * @param game the current game instance
     */
    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        playButton = createPlayButton("assets/play.png");

        settingsButton = createSettingsButton("assets/settings.png");

        logoutButton = createLogoutButton("assets/logout.png");

        exitButton = createExitButton("assets/exit.png");

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



    private ImageButton createPlayButton(String path) {
        ImageButton plButton = createButton(path);
        plButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new ChooseGameScreen(game));
                    }

                });
        plButton.setPosition(230, 320);
        stage.addActor(plButton);
        return plButton;
    }

    private ImageButton createSettingsButton(String path) {
        ImageButton settButton = createButton(path);
        settButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new SettingsScreen(game));
                    }

                });
        settButton.setPosition(230, 250);
        stage.addActor(settButton);
        return settButton;
    }

    private ImageButton createLogoutButton(String path) {
        ImageButton loButton = createButton(path);
        loButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        game.setScreen(new LoginScreen(game));
                    }

                });
        loButton.setPosition(230, 180);
        stage.addActor(loButton);

        return loButton;
    }

    private ImageButton createExitButton(String path) {
        ImageButton exButton = createButton(path);
        exButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dispose();
                        Gdx.app.exit();
                    }

                });
        exButton.setPosition(230, 110);
        stage.addActor(exButton);

        return exButton;
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
        game.font.getData().setScale(1.4f);
        game.font.draw(game.spriteBatch, "Welcome, " + "<username>" + "!!!", 230, 450);
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
//        stage.dispose();
    }
}
