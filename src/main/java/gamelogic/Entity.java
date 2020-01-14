package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

public interface Entity {

    public void update(float delta);
    public void render(AirHockeyGame game, Texture texture);
}
