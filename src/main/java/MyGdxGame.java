import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
    protected transient SpriteBatch spriteBatch;
    protected transient BitmapFont font;

    @Override
	public void create() {
    	spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new LoginScreen(this));
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
}
