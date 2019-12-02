package gamelogic;

public class CollisionsEngine {

    private transient Puck puck;
    private transient Paddle paddle1;
    private transient Paddle paddle2;

    /**
     * Constructor.
     * @param puck The puck.
     * @param paddle1 Player 1 paddle.
     * @param paddle2 Player 2 paddle.
     */
    public CollisionsEngine(Puck puck, Paddle paddle1, Paddle paddle2) {
        this.puck = puck;
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
    }

    /**
     * Method that calculates the collisions between the puck and paddles.
     */
    public void collide() {
        if (isIntersecting(puck, paddle1)) {
            this.puck.setXspeed(paddle1.getXspeed() + 50);
            this.puck.setYspeed(paddle1.getYspeed() + 50);
        }

        if (isIntersecting(puck, paddle2)) {
            this.puck.setXspeed(paddle2.getXspeed() + 50);
            this.puck.setYspeed(paddle2.getYspeed() + 50);
        }

    }

    public double distance(Collidable c1, Collidable c2) {
        return Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2);
    }

    public boolean isIntersecting(Collidable c1, Collidable c2) {
        return distance(c1, c2) <= Math.pow((c1.radius + c2.radius), 2);
    }

    /**
     * Get the angle between the two circles.
     * @param c1 First circle.
     * @param c2 The other circle.
     * @return The angle between them (relative to the x-axis in radians).
     */
    public double angleBetween(Collidable c1, Collidable c2) {
        float xdiff = c2.x - c1.x;
        float ydiff = c2.y - c1.y;

        double a = (double) ydiff / xdiff;

        return Math.atan(a);
    }

    /**
     * Get the angle that the circle is moving in.
     * @param c The circle.
     * @return The angle of movement (relative to the x-axis in radians).
     */
    public double angleOfMovement(Collidable c) {
        return Math.atan((double) c.getYspeed() / c.getXspeed());
    }

    /**
     * Method to get the speed in the i direction (x direction relative to the angle of collision).
     * @param c The circle.
     * @param theta The angle of collision.
     * @return The speed in the i direction.
     */
    public float getispeed(Collidable c, double theta) {
        double iangle = angleOfMovement(c) - theta;

        double speed = c.getXspeed() * Math.cos(iangle);
        return (float) speed;
    }

    /**
     * Method to get the speed in the j direction (y direction relative to the angle of collision).
     * @param c The circle.
     * @param theta The angle of collision.
     * @return The speed in the j direction.
     */
    public float getjspeed(Collidable c, double theta) {

        double iangle = angleOfMovement(c) - theta;

        double speed = c.getYspeed() * Math.sin(iangle);
        return (float) speed;
    }
}
