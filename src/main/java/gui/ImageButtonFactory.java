package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonFactory implements AbstractButtonFactory {

    final transient AirHockeyGame game;
    private transient Screen screen;

    public ImageButtonFactory(AirHockeyGame game, Screen screen) {
        this.screen = screen;
        this.game = game;
    }

    /**
     * Creator for basic ImageButton.
     *
     * @param path to the image resource
     * @return the created ImageButton
     */
    public Button createButton(String path) {
        Texture myTexture = new Texture(Gdx.files.internal(path));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);

        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setWidth(200);
        button.setHeight(100);

        return button;
    }

    /**
     * Creator for ImageButton that is used to transition between screens
     *  or exit the application.
     *
     * @param path to the image resource
     * @param newScreen type of Screen to transition to
     * @return the created ImageButton
     * @throws IllegalArgumentException if the specified screen type is not defined
     */
    public Button createTransButton(String path, String newScreen) {

        Button button = createButton(path);

        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        screen.dispose();
                        switch (newScreen) {
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
                                game.setScreen(new AuthenticationScreen(game));
                                break;
                            case "Exit":
                                Gdx.app.exit();
                                break;
                            case "Credentials":
                                game.setScreen(new LoginScreen(game));
                                break;
                            case "Registration":
                                game.setScreen(new RegistrationScreen(game));
                                break;
                            default:
                                throw new IllegalArgumentException("Screen type does not exist");

                        }
                    }
                }
        );
        return button;
    }

}
