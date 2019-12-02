package gamelogic;

import com.badlogic.gdx.math.Circle;

public class Puck extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient boolean initMove;

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


    public boolean isInitMove() {
        return initMove;
    }

    public void setInitMove(boolean initMove) {
        this.initMove = initMove;
    }
}
