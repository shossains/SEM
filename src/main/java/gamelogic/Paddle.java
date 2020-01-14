package gamelogic;

public class Paddle extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient PlayerType playerType;
    private transient float xupper;
    private transient float xlower;

    private transient float maxSpeed;
    private transient float acceleration;
    private transient float lowSpeed;

    /**
     * Constructor.
     * The paddle is what the player controls, and is what moves and interacts with the puck.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param xspeed Speed in y direction.
     * @param yspeed Speed in x direction.
     * @param radius Radius.
     */
    public Paddle(float x, float y, float xspeed, float yspeed, float radius, float mass,
                  float width, float height, PlayerType playerType, float maxSpeed,
                  float acceleration, float lowSpeed) {

        super(x, y, radius, xspeed, yspeed, mass, width, height);

        this.playerType = playerType;

        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.lowSpeed = lowSpeed;

        //set the X boundaries based on whether it is the first or the second player puck
        if (this.playerType == PlayerType.PLAYER1) {
            xupper = getWidth();
            xlower = getWidth() / 2;
        } else {
            xupper = getWidth() / 2;
            xlower = 0;
        }
    }

    /**
     * Method so the player move the paddle based on the keys they have pressed.
     * This is a very important method as it is how the player actively controls
     * their paddle.
     * I plan to add an interface in future for the movement of the puck
     * @param rightPressed If right is pressed.
     * @param leftPressed If left is pressed.
     * @param upPressed If up is pressed.
     * @param downPressed If down is pressed.
     */
    public void setSpeeds(boolean rightPressed, boolean leftPressed,
                           boolean upPressed, boolean downPressed) {

        setLateralSpeeds(rightPressed, leftPressed);

        setVerticalSpeeds(upPressed, downPressed);
    }

    /**
     * Sets the speeds in lateral directions.
     * @param rightPressed If the right button is pressed.
     * @param leftPressed If left is pressed.
     */
    public void setLateralSpeeds(boolean rightPressed, boolean leftPressed) {

        setLeftSpeed(rightPressed, leftPressed);
        setRightSpeed(rightPressed, leftPressed);

        if ((!leftPressed & !rightPressed) || (leftPressed & rightPressed)) {
            this.setXspeed(this.getXspeed() * 0.2f);
        }
    }

    /**
     * Sets the speeds in the vertical directions.
     * @param upPressed If up is pressed.
     * @param downPressed If down is pressed.
     */
    public void setVerticalSpeeds(boolean upPressed, boolean downPressed) {

        setUpwardsSpeed(upPressed, downPressed);

        setDownwardsSpeed(upPressed, downPressed);

        if ((!upPressed & !downPressed) || (upPressed & downPressed)) {
            this.setYspeed(this.getYspeed() * 0.2f);
        }
    }

    /**
     * Sets the speed in the positive Y direction.
     * @param upPressed If up is pressed.
     * @param downPressed If down is pressed.
     */
    public void setUpwardsSpeed(boolean upPressed, boolean downPressed) {

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
    }

    /**
     * Sets the speed in the negative Y direction.
     * @param upPressed If up is pressed.
     * @param downPressed If down is pressed.
     */
    public void setDownwardsSpeed(boolean upPressed, boolean downPressed) {

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
    }

    /**
     * Sets the speed in the negative X direction.
     * @param rightPressed If right is pressed.
     * @param leftPressed If left is pressed.
     */
    public void setLeftSpeed(boolean rightPressed, boolean leftPressed) {

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
    }

    /**
     * Set the speed in the positive X direction.
     * @param rightPressed If right is pressed.
     * @param leftPressed If left is pressed.
     */
    public void setRightSpeed(boolean rightPressed, boolean leftPressed) {

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
    }

    /**
     * This method makes sure the paddle is in the correct X boundaries.
     */
    public void fixXPosition() {
        if (this.x - this.radius < xlower) {
            this.x = xlower + this.radius;
        }
        if (this.x > xupper - this.radius) {
            this.x = xupper - this.radius;
        }
    }

    /**
     * This method makes sure the paddle is in the correct Y boundaries.
     */
    public void fixYPosition() {
        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
        }
        if (this.y > getHeight() - this.radius) {
            this.y = getHeight() - this.radius;
        }
    }
}
