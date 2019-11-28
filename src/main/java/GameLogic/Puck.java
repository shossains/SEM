package GameLogic;

import com.badlogic.gdx.math.Circle;

public class Puck extends Circle implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    //add extra attributes
    //It needs speed in x and y direction

    public transient float xSpeed;
    public transient float ySpeed;

    private transient boolean initMove;

    /**
     * Constructor
     * @param x
     * @param y
     * @param xSpeed
     * @param ySpeed
     * @param radius
     */
    public Puck(float x, float y, float xSpeed, float ySpeed, float radius) {

        super(x, y, radius);

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * Method to move the puck
     * @param deltaTime
     */
    public void movePuck(float deltaTime) {
        this.x += this.xSpeed*deltaTime;
        this.y += this.ySpeed*deltaTime;
    }

    /**
     * Method to ensure the puck is within the correct boundaries
     */
    public void fixPosition() {
        //we need to add the functionality to check that if the puck has hit the boundaries
        //or has been hit by a paddle

        //boundary detection
        //the puck.x and y represent the center of the circle

        //change later to reverse the speed and so on, but we need a co-efficient of friction
        //and more physics stuff
        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
        }
        if (this.x > 1280 - this.radius) {
            this.x = 1280 - this.radius;
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
            //also set the initMove to false;
            initMove = false;
        }
        if (this.y > 720 - this.radius) {
            this.y = 720 - this.radius;

            initMove = false;
        }
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public boolean isInitMove() {
        return initMove;
    }

    public void setInitMove(boolean initMove) {
        this.initMove = initMove;
    }
}
