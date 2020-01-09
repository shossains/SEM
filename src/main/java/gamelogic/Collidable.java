package gamelogic;

import com.badlogic.gdx.math.Circle;

public abstract class Collidable extends Circle implements java.io.Serializable {

    //Using the template method.

    public static final transient long serialVersionUID = 4328744;

    private float xspeed;
    private float yspeed;
    private float mass;
    private float width;
    private float height;

    /**
     * Constructor.
     * This is the super class for the puck and the paddle.
     * These classes utilise the template method.
     * The puck and paddle are the main elements of the actual air hockey game.
     * The pucks can move the paddle, which can in turn score.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param radius The radius.
     * @param xspeed Speed in x direction.
     * @param yspeed Speed in y direction.
     * @param mass The mass of the object (somewhat arbitrary, but the important part is the
     *             ratio between the masses of the objects in collision).
     */
    public Collidable(float x, float y, float radius, float xspeed, float yspeed, float mass,
                      float width, float height) {
        super(x, y, radius);
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.mass = mass;
        this.width = width;
        this.height = height;
    }

    /**
     * Method to move the object.
     * This will be called every time the game updates (per frame).
     * @param deltaTime The game time that has passed since the last call.
     */
    public void move(float deltaTime) {
        setX(this.x += getXspeed() * deltaTime);
        setY(this.y += getYspeed() * deltaTime);
    }

    /**
     * The method to ensure the object stays within bounds.
     */
    public void fixPosition() {
        fixXPosition();
        fixYPosition();
    }

    public abstract void fixXPosition();

    public abstract void fixYPosition();

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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}