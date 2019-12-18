import com.badlogic.gdx.math.Circle;

public class Paddle extends Circle implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    public transient float xspeed;
    public transient float yspeed;

    /**
     * Constructor.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param xspeed Speed in y direction.
     * @param yspeed Speed in x direction.
     * @param radius Radius.
     */
    public Paddle(float x, float y, float xspeed, float yspeed, float radius) {

        super(x, y, radius);

        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }

    /**
     * Method to move the paddle, this can be expanded later to be a more complex and realistic.
     * @param rightPressed If right is pressed.
     * @param leftPressed If left is pressed.
     * @param upPressed If up is pressed.
     * @param downPressed If down is pressed.
     */
    public void setSpeeds(boolean rightPressed, boolean leftPressed,
                           boolean upPressed, boolean downPressed) {
        if (rightPressed) {
            this.setXspeed(100);
        }

        if (leftPressed) {
            this.setXspeed(-100);
        }

        if ((!leftPressed & !rightPressed) || (leftPressed & rightPressed)) {
            this.setXspeed(0);
        }

        if (upPressed) {
            this.setYspeed(100);
        }

        if (downPressed) {
            this.setYspeed(-100);
        }

        if ((!upPressed & !downPressed) || (upPressed & downPressed)) {
            this.setYspeed(0);
        }
    }

    public void movePaddle(double deltaTime) {
        this.x += this.xspeed * deltaTime;
        this.y += this.yspeed * deltaTime;
    }

    /**
     * Method to ensure the puck is within the correct boundaries.
     */
    public void fixPosition() {

        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
        }
        if (this.x > 1280 - this.radius) {
            this.x = 1280 - this.radius;
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
        }
        if (this.y > 720 - this.radius) {
            this.y = 720 - this.radius;
        }
    }

    public float getXspeed() {
        return xspeed;
    }

    public void setXspeed(float xspeed) {
        this.xspeed = xspeed;
    }

    public float getYspeed() {
        return yspeed;
    }

    public void setYspeed(float yspeed) {
        this.yspeed = yspeed;
    }


}
