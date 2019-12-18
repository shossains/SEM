import com.badlogic.gdx.math.Circle;

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

    public double distance(Circle c1, Circle c2) {
        return Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2);
    }

    public boolean isIntersecting(Circle c1, Circle c2) {
        return distance(c1, c2) <= Math.pow((c1.radius + c2.radius), 2);
    }
}
