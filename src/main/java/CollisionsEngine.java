package gamelogic;

import com.badlogic.gdx.math.Circle;

public class CollisionsEngine {

    private transient Puck puck;
    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient float coefficientr;

    /**
     * Constructor.
     * @param puck The puck.
     * @param paddle1 Player 1 paddle.
     * @param paddle2 Player 2 paddle.
     * @param e The co-efficient of restitution (how much speed is kept after the collision).
     */
    public CollisionsEngine(Puck puck, Paddle paddle1, Paddle paddle2, float e) {
        this.puck = puck;
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        this.coefficientr = e;
    }

    /**
     * Method that calculates the collisions between the puck and paddles.
     */
    public void collide() {
        if (isIntersecting(puck, paddle1)) {
            newSpeeds(puck, paddle1);
        }
        if (isIntersecting(puck, paddle2)) {
            newSpeeds(puck, paddle2);
        }
    }

    public double distance(Collidable c1, Collidable c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2));
    }

    public boolean isIntersecting(Collidable c1, Collidable c2) {
        return distance(c1, c2) <= (c1.radius + c2.radius);
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
        double a = Math.atan2(ydiff, xdiff);
        return a;
    }

    /**
     * Method to get the speed in the i direction (x direction relative to the angle of collision).
     * @param c The circle.
     * @param theta The angle of collision.
     * @return The speed in the i direction.
     */
    public float getispeed(Collidable c, double theta) {

        double speed = Math.cos(theta) * c.getXspeed() + Math.sin(theta) * c.getYspeed();
        return (float) speed;
    }

    /**
     * Method to get the speed in the j direction (y direction relative to the angle of collision).
     * @param c The circle.
     * @param theta The angle of collision.
     * @return The speed in the j direction.
     */
    public float getjspeed(Collidable c, double theta) {

        double speed = Math.cos(theta) * c.getYspeed() - Math.sin(theta) * c.getXspeed();
        return (float) speed;
    }

    public float rotateToX(float vi, float vj, double theta) {
        double speed = Math.cos(theta) * vi - Math.sin(theta) * vj;
        return (float) speed;
    }

    public float rotateToY(float vi, float vj, double theta) {
        double speed = Math.cos(theta) * vj + Math.sin(theta) * vi;
        return (float) speed;
    }

    /**
     * Method to solve the COM and NEL equations and get the speeds after the collision.
     * @param m1 Mass 1.
     * @param m2 Mass 2.
     * @param u1 Initial speed 1.
     * @param u2 Initial speed 2.
     * @param ecurr Co-efficient of restitution.
     * @return The resulting speeds v1 and v2.
     */
    public float[] solveSimultaneous(float m1, float m2, float u1, float u2, float ecurr) {

        float const1 = m1 * u1 + m2 * u2;

        float const2 = - ecurr * (u1 - u2);

        float v1 = (const1 / m2 + const2) / (1 + m1 / m2);

        float v2 = v1 - const2;

        float[] v1v2 = {v1, v2};
        return v1v2;
    }

    /**
     * Method to update the speeds of the objects.
     * @param c1 The first object in the collision.
     * @param c2 The second object in the collision.
     */
    public void newSpeeds(Collidable c1, Collidable c2) {

        double theta  = angleBetween(c1, c2);

        float u1j = getjspeed(c1, theta);
        float u2j = getjspeed(c2, theta);

        float m1 = c1.getMass();
        float u1i = getispeed(c1, theta);

        float m2 = c2.getMass();
        float u2i = getispeed(c2, theta);

        //PCM: m1u1 + m2u2 = m1v1 + m2v2
        //NEL: (v1 - v2) = -e(u1 - u2)

        float[] v1v2 = solveSimultaneous(m1, m2, u1i, u2i, coefficientr);

        float v1i = v1v2[0];
        float v2i = v1v2[1];

        //the speed in the j direction will stay the same, as the collision was in the i axis only
        //but now we need to translate back to x and y axis
        //but we have for c1: v1 i +

        float c1x = rotateToX(v1i, u1j, theta);
        float c1y = rotateToY(v1i, u1j, theta);
        c1.setXspeed(c1x);
        c1.setYspeed(c1y);

        float c2x = rotateToX(v2i, u2j, theta);
        float c2y = rotateToY(v2i, u2j, theta);
        c2.setXspeed(c2x);
        c2.setYspeed(c2y);

        //move the puck and paddle a little so they don't stick together
        c1.move(0.01f);
        c2.move(0.01f);

    }
}
