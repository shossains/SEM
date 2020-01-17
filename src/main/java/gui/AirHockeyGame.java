package gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is called first when the project
 * is running. It redirects the user to the Authentication
 * screen and it has control over the sound effects.
 */
public class AirHockeyGame extends Game {

    public static final int S_WIDTH = 1280;
    public static final int S_HEIGHT = 720;

    public transient SpriteBatch spriteBatch;
    protected transient BitmapFont font;

    transient Music sound;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        //this.setScreen(new AuthenticationScreen(this));
        this.setScreen(new AuthenticationScreen(this));

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
     * Controller for pausing or resuming the sound effects.
     */
    public void muteUnmute() {
        if (sound.isPlaying()) {
            sound.pause();
        } else {
            sound.play();
        }
    }
}
