package gamelogic;

public class Puck extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    /**
     * The co-efficient of restitution.
     */
    private static final transient float e = 0.85f;

    /**
     * Constructor.
     * The Puck is what is used to actually play the game. It interacts with the paddles
     * (the paddles can move it), and it is what can go into the goals and increase the score.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param xspeed Speed in x direction.
     * @param yspeed Speed in y direction.
     * @param radius The radius.
     */
    public Puck(float x, float y, float xspeed, float yspeed, float radius, float mass, float width,
                float height) {
        super(x, y, radius, xspeed, yspeed, mass, width, height);
    }

    /**
     * This method makes sure the paddle is in the correct X boundaries.
     * We check if the puck is outside of the board boundaries,
     * and if it has hit an edge we calculate the speed after the resulting collision.
     */
    public void fixXPosition() {
        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
            this.setXspeed(- this.getXspeed() * e);
        }
        if (this.x > getWidth() - this.radius) {
            this.x = getWidth() - this.radius;
            this.setXspeed(- this.getXspeed() * e);
        }
    }

    /**
     * This method makes sure the paddle is in the correct Y boundaries.
     * We check if the puck is outside of the board boundaries,
     * and if it has hit an edge we calculate the speed after the resulting collision.
     */
    public void fixYPosition() {
        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
            this.setYspeed(- this.getYspeed() * e);
        }
        if (this.y > getHeight() - this.radius) {
            this.y = getHeight() - this.radius;
            this.setYspeed(- this.getYspeed() * e);
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

}
