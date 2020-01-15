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
    public final Goal goal1;
    public final Goal goal2;

    private transient EntityType entityType = EntityType.BOARD;

    /**
     * The board object.
     * @param x x coordinate
     * @param y y coordinate
     * @param width Boards width
     * @param height Boards height
     * @param goal1 Goal of the Player 1
     * @param goal2 Goal of the Player 2
     */
    public Board(float x, float y, float width, float height, Goal goal1, Goal goal2) {
        super(x, y, width, height);
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public Goal getGoal1() {
        return this.goal1;
    }

    public Goal getGoal2() {
        return this.goal2;
    }

    @Override
    public void update(float delta) {
        this.getGoal1().getScoringSystem().hud.updateTime(delta);
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
