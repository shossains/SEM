package scoring;

import com.badlogic.gdx.math.Rectangle;

import java.io.Serializable;

public class Board extends Rectangle implements Serializable {

    private static final long serialVersionUID = 1L;
    public final Goal goalLeft;
    public final Goal goalRight;

    /**
     * The board object.
     * @param x x coordinate
     * @param y y coordinate
     * @param width Boards width
     * @param height Boards height
     */
    public Board(float x, float y, float width, float height, Goal goalLeft, Goal goalRight) {
        super(x, y, width, height);
        this.goalLeft = goalLeft;
        this.goalRight = goalRight;
    }

    public Goal getGoalLeft() {
        return goalLeft;
    }

    public Goal getGoalRight() {
        return goalRight;
    }
}
