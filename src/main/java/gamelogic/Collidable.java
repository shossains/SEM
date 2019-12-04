package gamelogic;

import com.badlogic.gdx.math.Circle;

public abstract class Collidable extends Circle implements java.io.Serializable {

    //Using the template method.

    public static final transient long serialVersionUID = 4328744;

    private float xspeed;
    private float yspeed;
    private float mass;

    /**
     * Constructor.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param radius The radius.
     * @param xspeed Speed in x direction.
     * @param yspeed Speed in y direction.
     * @param mass The mass of the object.
     */
    public Collidable(float x, float y, float radius, float xspeed, float yspeed, float mass) {
        super(x, y, radius);
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.mass = mass;
    }

    /**
     * Method to move the object.
     * @param deltaTime The game time that has passed since the last call.
     */
    public void move(float deltaTime) {
        setX(this.x += getXspeed() * deltaTime);
        setY(this.y += getYspeed() * deltaTime);
    }

    /**
     * The method to ensure the object stays within bounds.
     */
    public abstract void fixPosition();

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

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}