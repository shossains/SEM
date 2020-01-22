package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import gui.AirHockeyGame;

import java.io.Serializable;

public class Board extends Rectangle implements Serializable, Entity {

    private static final long serialVersionUID = 1L;

    public final Goal goalOne;
    public final Goal goalTwo;

    private transient EntityType entityType = EntityType.BOARD;

    /**
     * The board object.
     * @param x x coordinate
     * @param y y coordinate
     * @param width Boards width
     * @param height Boards height
     * @param goalOne Goal of the Player 1
     * @param goalTwo Goal of the Player 2
     */
    public Board(float x, float y, float width, float height, Goal goalOne, Goal goalTwo) {
        super(x, y, width, height);
        this.goalOne = goalOne;
        this.goalTwo = goalTwo;
    }

    public Goal getGoalOne() {
        return this.goalOne;
    }

    public Goal getGoalTwo() {
        return this.goalTwo;
    }

    @Override
    public void update(float delta) {
        this.getGoalOne().getScoringSystem().hud.updateTime(delta);
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
