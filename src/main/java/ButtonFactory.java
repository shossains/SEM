import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonFactory {

    final transient MyGdxGame game;
    private transient Screen screen;

    public ButtonFactory(MyGdxGame game, Screen screen) {
        this.screen = screen;
        this.game = game;
    }

    public ImageButton createImageButton(String path) {
        Texture myTexture = new Texture(Gdx.files.internal(path));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);

        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setWidth(200);
        button.setHeight(100);

        return button;
    }

    public ImageButton createTransitionImageButton(String path, String newScreen) {

        ImageButton button = createImageButton(path);

        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        screen.dispose();
                        switch(newScreen) {
                            case "MainMenuScreen":
                                game.setScreen(new MainMenuScreen(game));
                                break;
                            case "SettingsScreen":
                                game.setScreen(new SettingsScreen(game));
                                break;
                            case "ChooseGameScreen":
                                game.setScreen(new ChooseGameScreen(game));
                                break;
                            case "GameScreen":
                                game.setScreen(new GameScreen(game));
                                break;
                            case "LoginScreen":
                                game.setScreen(new LoginScreen(game));
                                break;
                            case "Exit":
                                Gdx.app.exit();
                            default:
                                throw new IllegalArgumentException("Screen type does not exist");

                        }
                    }
                }
        );
        return button;
    }

}
