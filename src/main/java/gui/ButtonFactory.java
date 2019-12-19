package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * The purpose of this class is to create different kind
 * of buttons and their functionality.
 * This class makes use of the gui.TextFieldFactory class which
 * creates a basic skin, needed for the Image Buttons.
 * This class is used by the graphical user interface.
 */
public class ButtonFactory {

    final transient MyGdxGame game;
    private transient Screen screen;

    public ButtonFactory(MyGdxGame game, Screen screen) {
        this.screen = screen;
        this.game = game;
    }

    /**
     * Creator for basic ImageButton.
     *
     * @param path to the image resource
     * @return the created ImageButton
     */
    public ImageButton createImButton(String path) {
        Texture myTexture = new Texture(Gdx.files.internal(path));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);

        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setWidth(200);
        button.setHeight(100);

        return button;
    }

    /**
     * Method for creating a text button, using a basic skin.
     * @param text for the button.
     * @return the actual text button.
     */
    public TextButton createTextButton(String text) {
        TextFieldFactory textFieldFactory = new TextFieldFactory(this.game, this.screen);
        TextButton textButton = new TextButton(text, textFieldFactory.createSkin());
        textButton.setSize(100, 50);
        return textButton;
    }

    /**
     * Method for creating a text button with a given functionality.
     * @param text the name of the button.
     * @param newScreen screen for redirection.
     * @return a new functional text button.
     */
    public TextButton createTransTextButton(String text, String newScreen) {
        TextButton button = createTextButton(text);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        screen.dispose();
                        switch (newScreen) {
                            case "LoginScreen":
                                game.setScreen(new LoginScreen(game));
                                break;
                            case "Exit":
                                Gdx.app.exit();
                                break;
                            case "ChooseGameScreen":
                                game.setScreen(new ChooseGameScreen(game));
                                break;
                            default:
                                throw new IllegalArgumentException("Screen type does not exist");

                        }
                    }
                }
        );
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
    public ImageButton createTransImButton(String path, String newScreen) {

        ImageButton button = createImButton(path);

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
                                game.setScreen(new LoginScreen(game));
                                break;
                            case "Exit":
                                Gdx.app.exit();
                                break;
                            case "Credentials":
                                game.setScreen(new Credentials(game));
                                break;
                            case "Registration":
                                game.setScreen(new Registration(game));
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
