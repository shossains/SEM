package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TextButtonFactory implements AbstractButtonFactory {

    final transient AirHockeyGame game;
    private transient Screen screen;

    public TextButtonFactory(AirHockeyGame game, Screen screen) {
        this.screen = screen;
        this.game = game;
    }

    /**
     * Method for creating a text button, using a basic skin.
     * @param text for the button.
     * @return the actual text button.
     */
    public Button createButton(String text) {
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
    public Button createTransButton(String text, String newScreen) {
        Button button = createButton(text);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        screen.dispose();
                        switch (newScreen) {
                            case "LoginScreen":
                                game.setScreen(new AuthenticationScreen(game));
                                break;
                            case "Exit":
                                Gdx.app.exit();
                                break;
                            case "ChooseGameScreen":
                                game.setScreen(new ChooseGameScreen(game));
                                break;
                            case "MainMenuScreen":
                                game.setScreen(new MainMenuScreen(game));
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
