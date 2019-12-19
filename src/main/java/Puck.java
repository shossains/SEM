import com.badlogic.gdx.math.Circle;

public class Puck extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient boolean initMove;

    /**
     * The co-efficient of restitution.
     */
    private static final transient float e = 0.85f;

    /**
     * Constructor.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param xspeed Speed in x direction.
     * @param yspeed Speed in y direction.
     * @param radius The radius.
     */
    public Puck(float x, float y, float xspeed, float yspeed, float radius, float mass) {

        super(x, y, radius, xspeed, yspeed, mass);
    }

    /**
     * Method to ensure the puck is within the correct boundaries.
     * We check if the puck is outside of the board boundaries,
     * and if it has hit an edge we calculate the speed after the resulting collision.
     */
    public void fixPosition() {

        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
            this.setXspeed(- this.getXspeed() * e);
        }
        if (this.x > 1280 - this.radius) {
            this.x = 1280 - this.radius;
            this.setXspeed(- this.getXspeed() * e);
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
            //also set the initMove to false;
            this.setYspeed(- this.getYspeed() * e);
            initMove = false;
        }
        if (this.y > 720 - this.radius) {
            this.y = 720 - this.radius;
            this.setYspeed(- this.getYspeed() * e);

            initMove = false;
        }
    }

    /**
     * Set the puck's position on the board to the initial one.
     */
    public void resetPosition() {
        this.setX(640f);
        this.setY(360f);
        this.setXspeed(0);
        this.setYspeed(0);
    }

    public boolean isInitMove() {
        return initMove;
    }

    public void setInitMove(boolean initMove) {
        this.initMove = initMove;
    }
}
