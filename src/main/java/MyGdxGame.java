import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

    public static final int S_WIDTH = 1280;
    public static final int S_HEIGHT = 720;

    protected transient SpriteBatch spriteBatch;
    protected transient BitmapFont font;

    transient Music sound;
    transient boolean mutePressed;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new LoginScreen(this));

        sound = Gdx.audio.newMusic(Gdx.files.internal("assets/test.ogg"));
        sound.setLooping(true);
        sound.play();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
    }

    /**
     * Controller for muting and unmuting the game.
     */
    public void muteUnmute() {
        if (sound.isPlaying()) {
            sound.pause();
        } else {
            sound.play();
        }
    }
}
