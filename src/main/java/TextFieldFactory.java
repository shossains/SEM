import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TextFieldFactory {

    final transient MyGdxGame game;
    private transient Screen screen;

    public TextFieldFactory(MyGdxGame game, Screen screen) {
        this.screen = screen;
        this.game = game;
    }

    public Skin createSkin() {
        AssetManager assetManager = new AssetManager();
        assetManager.load("assets/uiskin.json", Skin.class);
        assetManager.finishLoading();
        Skin skin = new Skin(Gdx.files.internal( "assets/uiskin.json"));
        return skin;
    }

    public TextField createTextField() {
        Skin skin = createSkin();
        TextField textField = new TextField("", skin);
        textField.setSize(300, 50);
        return textField;
    }
}
