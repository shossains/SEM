package gamelogic;

import com.badlogic.gdx.math.Circle;

public class Puck extends Circle implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    //add extra attributes
    //It needs speed in x and y direction

    public transient float xspeed;
    public transient float yspeed;

    private transient boolean initMove;

    /**
     * Constructor.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param xspeed Speed in x direction.
     * @param yspeed Speed in y direction.
     * @param radius The radius.
     */
    public Puck(float x, float y, float xspeed, float yspeed, float radius) {

        super(x, y, radius);

        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }

    /**
     * Method to move the puck.
     * @param deltaTime GDX deltaTime.
     */
    public void movePuck(float deltaTime) {
        this.x += this.xspeed * deltaTime;
        this.y += this.yspeed * deltaTime;
    }

    /**
     * Method to ensure the puck is within the correct boundaries.
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
            this.setXspeed(- this.getXspeed());
        }
        if (this.x > 1280 - this.radius) {
            this.x = 1280 - this.radius;
            this.setXspeed(- this.getXspeed());
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
            //also set the initMove to false;
            this.setYspeed(- this.getYspeed());
            initMove = false;
        }
        if (this.y > 720 - this.radius) {
            this.y = 720 - this.radius;
            this.setYspeed(- this.getYspeed());

            initMove = false;
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

    public boolean isInitMove() {
        return initMove;
    }

    public void setInitMove(boolean initMove) {
        this.initMove = initMove;
    }
}
