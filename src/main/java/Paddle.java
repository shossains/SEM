package gamelogic;

public class Paddle extends Collidable implements java.io.Serializable {
import com.badlogic.gdx.math.Circle;

    public static final transient long serialVersionUID = 4328743;

    private transient PlayerType playerType;
    private transient float xupper = 1280;
    private transient float xlower = 0;

    private transient float maxSpeed = 200;
    private transient float acceleration = 6;
    private transient float lowSpeed = 50;

    /**
     * Constructor.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param xspeed Speed in y direction.
     * @param yspeed Speed in x direction.
     * @param radius Radius.
     */
    public Paddle(float x, float y, float xspeed, float yspeed, float radius, float mass,
                  PlayerType playerType) {

        super(x, y, radius, xspeed, yspeed, mass);

        this.playerType = playerType;
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
        if (rightPressed && !leftPressed) {
            if (this.getXspeed() < maxSpeed) {
                if (this.getXspeed() < lowSpeed) {
                    //set a baseline
                    this.setXspeed(50);
                } else {
                    this.setXspeed(this.getXspeed() + acceleration);
                }
            }
        }

        if (leftPressed && !rightPressed) {
            if (this.getXspeed() > -maxSpeed) {
                if (this.getXspeed() > - lowSpeed) {
                    //set a baseline
                    this.setXspeed(-50);
                } else {
                    this.setXspeed(this.getXspeed() - acceleration);
                }
            }
        }

        if ((!leftPressed & !rightPressed) || (leftPressed & rightPressed)) {
            this.setXspeed(this.getXspeed() * 0.2f);
        }

        if (upPressed && !downPressed) {
            if (this.getYspeed() < maxSpeed) {
                if (this.getYspeed() < lowSpeed) {
                    //set a baseline
                    this.setYspeed(50);
                } else {
                    this.setYspeed(this.getYspeed() + acceleration);
                }
            }
        }

        if (downPressed && !upPressed) {
            if (this.getYspeed() > -maxSpeed) {
                if (this.getYspeed() > - lowSpeed) {
                    //set a baseline
                    this.setYspeed(-50);
                } else {
                    this.setYspeed(this.getYspeed() - acceleration);
                }
            }
        }

        if ((!upPressed & !downPressed) || (upPressed & downPressed)) {
            this.setYspeed(this.getYspeed() * 0.2f);
        }
    }

    /**
     * Method to ensure the puck is within the correct boundaries.
     */
    public void fixPosition() {

        if (this.playerType == PlayerType.PLAYER1) {
            xupper = 1280;
            xlower = 640;
        } else if (this.playerType == PlayerType.PLAYER2) {
            xupper = 640;
            xlower = 0;
        }

        if (this.x - this.radius < xlower) {
            this.x = xlower + this.radius;
        }
        if (this.x > xupper - this.radius) {
            this.x = xupper - this.radius;
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
        }
        if (this.y > 720 - this.radius) {
            this.y = 720 - this.radius;
        }
    }
}
