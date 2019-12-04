package gamelogic;

import org.lwjgl.Sys;

public class CollisionsEngine {

    private transient Puck puck;
    private transient Paddle paddle1;
    private transient Paddle paddle2;
    private transient float e;

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
        this.e = e;
    }

    /**
     * Method that calculates the collisions between the puck and paddles.
     */
    public void collide() {
        System.out.println(puck.x + " " + puck.y);
        System.out.println(puck.getXspeed());
        if (isIntersecting(puck, paddle1)) {

            newSpeeds(puck, paddle1);
            //this.puck.setXspeed(paddle1.getXspeed() + 50);
            //this.puck.setYspeed(paddle1.getYspeed() + 50);
        }

        if (isIntersecting(puck, paddle2)) {

            newSpeeds(puck, paddle2);
            //this.puck.setXspeed(paddle2.getXspeed() + 50);
            //this.puck.setYspeed(paddle2.getYspeed() + 50);
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

    public double totalSpeed(Collidable c) {
        return Math.sqrt(Math.pow(c.getXspeed(), 2) + Math.pow(c.getYspeed(), 2));
    }

    public float[] solveSimultaneous(float m1, float m2, float u1, float u2, float ecurr) {

        float const1 = m1 * u1 + m2 * u2;
        System.out.println("const1 " + const1);

        float u1negu2 = u1 - u2;
        System.out.println(u1negu2);
        float const2 = - ecurr * (u1 - u2);

        System.out.println("const2 " + const2);

        float v1 = (const1/m2 + const2)/(1 + m1/m2);

        float v2 = v1 - const2;

        float[] v1v2 = {v1, v2};
        return v1v2;
    }

    public void newSpeeds(Collidable c1, Collidable c2) {

        //add and edge case that deals with if there isnt actually a collision in a certain axis

        double theta  = angleBetween(c1, c2);

        float u1j = getjspeed(c1, theta);
        float u2j = getjspeed(c1, theta);

        float m1 = c1.getMass();
        float u1i = getispeed(c1, theta);

        float m2 = c2.getMass();
        float u2i = getispeed(c2, theta);

        //PCM: m1u1 + m2u2 = m1v1 + m2v2
        //NEL: (v1 - v2) = -e(u1 - u2)

        //const1 = m1u1 + m2u2
        float const1 = m1 * u1i + m2 * u2i;

        //const2 = -e(u1 - u2)
        float const2 = - e * (u1i - u2i);

        //v1 = (const1/m2 + c2)/(1 + m1/m2)
        float v1i = (const1/m2 + const2)/(1 + m1/m2);

        //v2 = v1 - c2

        float v2i = v1i - const2;

        //the speed in the j direction will stay the same, as the collision was in the i axis only
        //but now we need to translate back to x and y axis
        //but we have for c1: v1 i +

        //new x = v1i cos(theta) + v1jcos(theta)
        //new y = v1isin(theta) + v1jsin(theta)
        float c1x = (float) (v1i * Math.cos(theta) + u1j * Math.cos(theta));
        float c2x = (float) (v2i * Math.cos(theta) + u2j * Math.cos(theta));

        float c1y = (float) (u1i * Math.sin(theta) + u1j * Math.sin(theta));
        float c2y = (float) (u2i * Math.sin(theta) + u2j * Math.sin(theta));

        c1.setXspeed(c1x);
        c1.setYspeed(c1y);

        c2.setXspeed(c2x);
        c2.setYspeed(c2y);

    }
}
