package scoring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import gamelogic.Entity;
import gamelogic.EntityType;
import gui.AirHockeyGame;

import java.io.Serializable;

public class Board extends Rectangle implements Serializable, Entity {

    private static final long serialVersionUID = 1L;

    private transient EntityType entityType = EntityType.BOARD;

    //private transient Texture boardImage = new Texture(Gdx.files.internal("assets/table.png"));

    /**
     * The board object.
     * @param x x coordinate
     * @param y y coordinate
     * @param width Boards width
     * @param height Boards height
     */
    public Board(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(AirHockeyGame game, Texture texture) {
        game.spriteBatch.draw(texture, this.x, this.y, this.width, this.height);
    }

    @Override
    public EntityType getEntityType() {
        return this.entityType;
    }
}
