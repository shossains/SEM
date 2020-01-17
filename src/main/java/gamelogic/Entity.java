package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

public interface Entity {

    void update(float delta);

    void render(AirHockeyGame game, Texture texture);

    EntityType getEntityType();
}
