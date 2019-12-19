package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * The purpose of this class is to create a simple
 * text field, based on a basic skin. It is used
 * for the graphical user interface, when user's
 * input is required.
 */
public class TextFieldFactory {

    final transient AirHockeyGame game;
    private transient Screen screen;

    public TextFieldFactory(AirHockeyGame game, Screen screen) {
        this.screen = screen;
        this.game = game;
    }

    /**
     * Method for creating a basic skins.
     * It is used by the text buttons and text fields.
     * @return a basic skin.
     */
    public Skin createSkin() {
        AssetManager assetManager = new AssetManager();
        assetManager.load("assets/uiskin.json", Skin.class);
        assetManager.finishLoading();
        Skin skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
        return skin;
    }

    /**
     * Create a simpe text field.
     * @return a new text field, using the skin created before.
     */
    public TextField createTextField() {
        Skin skin = createSkin();
        TextField textField = new TextField("", skin);
        textField.setSize(300, 50);
        return textField;
    }
}
