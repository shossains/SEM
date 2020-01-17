package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

public class Paddle extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient PlayerType playerType;
    private transient EntityType entityType = EntityType.PADDLE;
    public transient float xupper;
    public transient float xlower;

    private transient float maxSpeed;
    private transient float acceleration;
    private transient float lowSpeed;

    public transient Direction direction;
    public transient InputHandler inputHandler;

    /**
     * Constructor.
     * The paddle is what the player controls, and is what moves and interacts with the puck.
     * @param builder PaddleBuilder used to create a Paddle object.
     */
    public Paddle(PaddleBuilder builder) {

        super(builder.x, builder.y, builder.radius, builder.xspeed, builder.yspeed, builder.mass, builder.width, builder.height);

        this.playerType = builder.playerType;

        this.maxSpeed = builder.maxSpeed;
        this.acceleration = builder.acceleration;
        this.lowSpeed = builder.lowSpeed;

        //set the X boundaries based on whether it is the first or the second player puck
        if (this.playerType == PlayerType.PLAYER1) {
            xupper = getWidth();
            xlower = getWidth() / 2;

            inputHandler = new Paddle1InputHandler();
        } else {
            xupper = getWidth() / 2;
            xlower = 0;

            inputHandler = new Paddle2InputHandler();
        }
    }

    /**
     * Method so the player move the paddle based on the keys they have pressed.
     * This is a very important method as it is how the player actively controls
     * their paddle.
     * I plan to add an interface in future for the movement of the puck
     */
    public void setSpeeds() {

        boolean rightPressed = direction.isRightPressed();
        boolean leftPressed = direction.isLeftPressed();
        boolean upPressed = direction.isUpPressed();
        boolean downPressed = direction.isDownPressed();

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
                    this.setYspeed(lowSpeed);
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
                    this.setYspeed(-lowSpeed);
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
                    this.setXspeed(-lowSpeed);
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
                    this.setXspeed(lowSpeed);
                } else {
                    this.setXspeed(this.getXspeed() + acceleration);
                }
            }
        }
    }


    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public void update(float delta) {
        //set the speeds
        //input handling
        direction = inputHandler.handleInput();

        this.setSpeeds();

        this.move(delta);

    }

    @Override
    public void render(AirHockeyGame game, Texture texture) {

        game.spriteBatch.draw(texture, this.x - this.radius, this.y - this.radius,
                this.radius * 2, this.radius * 2);

    }

    @Override
    public EntityType getEntityType() {
        return this.entityType;
    }

    public static class PaddleBuilder {

        protected transient float x;
        protected transient float y;
        protected transient float xspeed;
        protected transient float yspeed;
        protected transient float radius;
        protected transient float mass;
        protected transient float width;
        protected transient float height;
        protected transient PlayerType playerType;
        protected transient float maxSpeed;
        protected transient float acceleration;
        protected transient float lowSpeed;

        public PaddleBuilder atX(float x) {
            this.x = x;
            return this;
        }

        public PaddleBuilder atY(float y) {
            this.y = y;
            return this;
        }

        public PaddleBuilder withSpeedX(float speedX) {
            this.xspeed = speedX;
            return this;
        }

        public PaddleBuilder withSpeedY(float speedY) {
            this.yspeed = speedY;
            return this;
        }

        public PaddleBuilder withRadius(float radius) {
            this.radius = radius;
            return this;
        }

        public PaddleBuilder withMass(float mass) {
            this.mass = mass;
            return this;
        }

        public PaddleBuilder onWidth(float width) {
            this.width = width;
            return this;
        }

        public PaddleBuilder onHeight(float height) {
            this.height = height;
            return this;
        }

        public PaddleBuilder withPlayerType(PlayerType playerType) {
            this.playerType = playerType;
            return this;
        }

        public PaddleBuilder withMaxSpeed(float maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }

        public PaddleBuilder withAcceleration(float acceleration) {
            this.acceleration = acceleration;
            return this;
        }

        public PaddleBuilder withLowSpeed(float lowSpeed) {
            this.lowSpeed = lowSpeed;
            return this;
        }

        public Paddle build() {
            return new Paddle(this);
        }

    }

}
