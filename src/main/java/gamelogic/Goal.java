package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;
import java.io.Serializable;
import scoring.BasicScoringSystem;



public class Goal implements Serializable, Entity {

    private static final long serialVersionUID = 13L;
    private transient EntityType entityType = EntityType.GOAL;
    private transient PlayerType playerType;

    public final float topPost;
    public final float bottomPost;
    public final float depth;

    public final BasicScoringSystem scoringSystem;

    /**
     * Constructor for the Goal Object.
     * @param topPost The Y coordinates of the left post of the goal.
     * @param bottomPost The Y coordinates of the right post of the goal.
     * @param depth The X coordinate indicating the goal line.
     * @param scoringSystem The scoring system used during the game, updated on goals.
     */
    public Goal(float topPost, float bottomPost, float depth,
                BasicScoringSystem scoringSystem, PlayerType playerType) {
        this.topPost = topPost;
        this.bottomPost = bottomPost;
        this.depth = depth;
        this.scoringSystem = scoringSystem;
        this.playerType = playerType;
    }

    public float getTopPost() {
        return this.topPost;
    }

    public float getBottomPost() {
        return this.bottomPost;
    }

    public float getDepth() {
        return this.depth;
    }

    public BasicScoringSystem getScoringSystem() {
        return this.scoringSystem;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(AirHockeyGame game, Texture texture) {
        game.spriteBatch.draw(texture, 0, 0);
    }

    @Override
    public EntityType getEntityType() {
        return this.entityType;
    }

    public PlayerType getPlayerType() {
        return  this.playerType;
    }
}
