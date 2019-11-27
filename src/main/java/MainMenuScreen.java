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
    public transient Texture myTexture1;
    public transient TextureRegion myTextureRegion1;
    public transient TextureRegionDrawable myTexRegionDrawable1;
    public transient Texture myTexture2;
    public transient TextureRegion myTextureRegion2;
    public transient TextureRegionDrawable myTexRegionDrawable2;
    public transient Label outputLabel;

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        ImageButton playButton = createButton("start_button_inactive.png",
                "start_button_active.png");
        ImageButton button2 = createButton("register.png", "register.png");
        playButton.setPosition(300, 200);
        button2.setPosition(300, 100);
        game.font.setColor(Color.RED);
        stage.addActor(playButton);
        stage.addActor(button2);
        outputLabel = new Label("label ",new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        outputLabel.setText("Please log in before playing the game.");
        outputLabel.setPosition(200, 400);
        playButton.add(outputLabel).expand().fill();
        stage.addActor(outputLabel);
    }

    public ImageButton createButton(String path1, String path2) {
        myTexture1 = new Texture(Gdx.files.internal(path1));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        myTexture2 = new Texture(Gdx.files.internal(path2));
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        playButton = new ImageButton(myTexRegionDrawable1,
                myTexRegionDrawable2);
        playButton.setHeight(100);
        playButton.setWidth(200);
        playButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.print("button pressed");
                        outputLabel.setText("pressed");
                    }
                }
                );
        return playButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)204 / 255, (float)204 / 255, 1, 1);
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
