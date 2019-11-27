package GameLogic;

import com.badlogic.gdx.math.Circle;

public class Paddle extends Circle implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    public transient float xSpeed;
    public transient float ySpeed;

    /**
     * Constructor.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param xSpeed Speed in y direction.
     * @param ySpeed Speed in x direction.
     * @param radius Radius.
     */
    public Paddle(float x, float y, float xSpeed, float ySpeed, float radius) {

        super(x, y, radius);

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * Method to move the paddle, this can be expanded later to be a more complex and realistic.
     * @param rightPressed If right is pressed.
     * @param leftPressed If left is pressed.
     * @param upPressed If up is pressed.
     * @param downPressed If down is pressed.
     * @param deltaTime Delta time from gdx.
     */
    public void movePaddle(boolean rightPressed, boolean leftPressed,
                           boolean upPressed, boolean downPressed,
                           float deltaTime) {
        if ( rightPressed ) {
            this.x += 30*deltaTime;
        }

        if (leftPressed) {
            this.x -= 30*deltaTime;
        }

        if (upPressed) {
            this.y += 30*deltaTime;
        }

        if (downPressed) {
            this.y -= 30*deltaTime;
        }

    }

    /**
     * Method to ensure the puck is within the correct boundaries.
     */
    public void fixPosition() {

        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
        }
        if (this.x > 400 - this.radius) {
            this.x = 400 - this.radius;
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
        }
        if (this.y > 300 - this.radius) {
            this.y = 300 - this.radius;
        }
    }


}
