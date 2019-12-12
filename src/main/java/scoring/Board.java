package scoring;

import com.badlogic.gdx.math.Rectangle;

import java.io.Serializable;

public class Board extends Rectangle implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
