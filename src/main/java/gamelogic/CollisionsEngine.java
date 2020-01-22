package gamelogic;

import com.badlogic.gdx.audio.Sound;

import scoring.Board;
import scoring.Goal;

public class CollisionsEngine {

    private transient float puckWalle;
    private transient float coefficientr;
    private transient Sound sound;

    PaddleCollisionHandler pach;
    PuckCollisionHandler puch;

    /**
     * Constructor.
     * The Collisions engine is the physics engine behind the
     * collisions between the puck and paddles.
     * Please see the collision theory md file for more documentation behind the mathematical theory
     * of the collisions.
     * @param e The co-efficient of restitution (how much speed is kept after the collision).
     */
    public CollisionsEngine(float e, float puckWalle, Sound sound) {
        this.coefficientr = e;
        this.sound = sound;
        this.puckWalle = puckWalle;

        pach = new PaddleCollisionHandler();
        puch = new PuckCollisionHandler(sound, puckWalle);
    }

    /**
     * Method to first see if two entities are colliding
     * and then collide them if necessary.
     * @param e1 First Entity.
     * @param e2 Second Entity.
     */
    public void collideEntities(Entity e1, Entity e2) {
        if (e1.getEntityType() == EntityType.PUCK && e2.getEntityType() == EntityType.PADDLE) {

            collide((Collidable) e1, (Collidable) e2);

        }
        if (e1.getEntityType() == EntityType.BOARD && e2.getEntityType() == EntityType.PUCK) {
            //checkGoal((Puck) e2, (Board) e1);
            collide((Puck) e2, (Board) e1);

        }

        if (e1.getEntityType() == EntityType.BOARD && e2.getEntityType() == EntityType.PADDLE) {

            collide((Paddle) e2, (Board) e1);

        }

        if (e1.getEntityType() == EntityType.GOAL && e2.getEntityType() == EntityType.PUCK) {
            checkGoal((Puck) e2, (Goal) e1);
        }
    }

    /**
     * Method to collide the Puck and the board if the puck is out of bounds.
     * @param puck The puck.
     * @param board The board.
     */
    public void collide(Puck puck, Board board) {
        puch.fixPuckPosition(puck, board);
    }

    /**
     * Method to collide the Paddle and the board if the puck is out of bounds.
     * @param paddle The paddle.
     * @param board The board.
     */
    public void collide(Paddle paddle, Board board) {
        pach.fixPaddlePosition(paddle, board);
    }

    /**
     * Method to collide two Collidable objects (Puck and Paddle).
     * @param c1 First Collidable.
     * @param c2 Second Collidable.
     */
    public void collide(Collidable c1, Collidable c2) {
        if (isIntersecting(c1, c2)) {
            newSpeeds(c1, c2);
            sound.play();
        }
    }

//    /**
//     * Method to fix x and y position of the puck and change its speed if necessary.
//     * @param puck The puck.
//     * @param board The board.
//     */
//    public void fixPuckPosition(Puck puck, Board board) {
//        fixPuckXPosition(puck, board);
//        fixPuckYPosition(puck, board);
//    }

//    /**
//     * Method to fix x and y position of the paddle.
//     * @param paddle The paddle.
//     * @param board The puck.
//     */
//    public void fixPaddlePosition(Paddle paddle, Board board) {
//        fixPaddleXposition(paddle, board);
//        fixPaddleYPosition(paddle, board);
//    }

//    /**
//     * Method to fix the position of the puck in the x axis if it is out of bounds.
//     * @param puck The puck.
//     * @param board The board with which it can collide.
//     */
//    public void fixPuckXPosition(Puck puck, Board board) {
//        if (puck.x - puck.radius < 0) {
//            puck.x = 0 + puck.radius;
//            puck.setXspeed(- puck.getXspeed() * puckWalle);
//            sound.play();
//        }
//        if (puck.x > board.width - puck.radius) {
//            puck.x = board.width - puck.radius;
//            puck.setXspeed(- puck.getXspeed() * puckWalle);
//            sound.play();
//        }
//    }
//
//    /**
//     * Method to fix the position of the puck in the y axis if it is out of bounds.
//     * @param puck The puck.
//     * @param board The board with which it can collide.
//     */
//    public void fixPuckYPosition(Puck puck, Board board) {
//        if (puck.y - puck.radius < 0) {
//            puck.y = 0 + puck.radius;
//            puck.setYspeed(- puck.getYspeed() * puckWalle);
//            sound.play();
//        }
//        if (puck.y > board.height - puck.radius) {
//            puck.y = board.height - puck.radius;
//            puck.setYspeed(- puck.getYspeed() * puckWalle);
//            sound.play();
//        }
//    }

//    /**
//     * Method to fix the position of the paddle in the x axis if it is out of bounds.
//     * @param paddle The paddle.
//     * @param board The board with which it can collide.
//     */
//    public void fixPaddleXposition(Paddle paddle, Board board) {
//
//        if (paddle.x - paddle.radius < paddle.xlower) {
//            paddle.x = paddle.xlower + paddle.radius;
//        }
//        if (paddle.x > paddle.xupper - paddle.radius) {
//            paddle.x = paddle.xupper - paddle.radius;
//        }
//
//    }
//
//    /**
//     * Method to fix the position of the puck in the y axis if it is out of bounds.
//     * @param paddle The puck.
//     * @param board The board with which it can collide.
//     */
//    public void fixPaddleYPosition(Paddle paddle, Board board) {
//        if (paddle.y - paddle.radius < 0) {
//            paddle.y = 0 + paddle.radius;
//        }
//        if (paddle.y > board.height - paddle.radius) {
//            paddle.y = board.height - paddle.radius;
//        }
//    }

    /**
     * Method that calculates the distance between the circles.
     * Used to check if the circles are intersecting.
     * @param c1 First circle.
     * @param c2 Second circle.
     * @return The distance between the centres of the circles.
     */
    public double distance(Collidable c1, Collidable c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x), 2) + Math.pow((c1.y - c2.y), 2));
    }

    /**
     * Method that checks if two circles are intersecting.
     * This method tells us whether the circles are actually colliding.
     * @param c1 First circle.
     * @param c2 Second circle.
     * @return Whether or not the circles are intersecting.
     */
    public boolean isIntersecting(Collidable c1, Collidable c2) {
        return distance(c1, c2) <= (c1.radius + c2.radius);
    }

    /**
     * Get the angle between the two circles.
     * This is needed to so we can translate the speeds and calculate an oblique collision.
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

    /**
     * Method to calculate the speed in the x direction of the circle.
     * This is needed as we must translate the speeds back to the axes we use for the normal
     * operations.
     * @param vi Speed in the i direction.
     * @param vj Speed in the j direction.
     * @param theta The angle of the collision.
     * @return The speed in the x axis.
     */
    public float rotateToX(float vi, float vj, double theta) {
        double speed = Math.cos(theta) * vi - Math.sin(theta) * vj;
        return (float) speed;
    }

    /**
     * Method to calculate the speed in the y direction of the circle.
     * This is needed as we must translate the speeds back to the axes we use for the normal
     * operations.
     * @param vi Speed in the i direction.
     * @param vj Speed in the j direction.
     * @param theta The angle of the collision.
     * @return The speed in the y axis.
     */
    public float rotateToY(float vi, float vj, double theta) {
        double speed = Math.cos(theta) * vj + Math.sin(theta) * vi;
        return (float) speed;
    }

    /**
     * Method to solve the COM and NEL equations and get the speeds after the collision.
     * Please check the collision theory documentation for a more natural explanation.
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
     * This is the method that integrates all of the separate methods and uses them to
     * calculate the resulting speeds.
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

        //now make sure the puck and paddle don't get stuck together

        fixCollision(c1, c2);

    }

    /**
     * This method ensures that when the puck and paddle collide,
     * they don't get stuck inside of each other.
     * @param c1 The first object in the collision.
     * @param c2 The second object in the collision.
     */
    public void fixCollision(Collidable c1, Collidable c2) {
        while (isIntersecting(c1, c2)) {
            //they should not be still colliding
            //move them away from each other so they are not colliding

            c1.move(0.01f);
            c2.move(0.01f);

        }
    }

    /**
     * Check if there was a goal scored by any of the players.
     * @param puck Puck
     * @param goal Goal
     */
    public void checkGoal(Puck puck, Goal goal) {
        if (goal.getPlayerType() == PlayerType.PLAYER1) {
            if (puck.x - (puck.radius / 2) <= goal.getDepth()
                    && puck.y - (puck.radius / 2) >= goal.getTopPost()
                    && puck.y + (puck.radius / 2) <= goal.getBottomPost()) {

                goal.getScoringSystem().goalPlayerTwo();
                goal.getScoringSystem().checkScorePlayerTwo();
                resetRight(puck);
            }
        } else {
            if (puck.x + (puck.radius / 2) >= goal.getDepth()
                    && puck.y + (puck.radius / 2) >= goal.getTopPost()
                    && puck.y + (puck.radius / 2) <= goal.getBottomPost()) {

                goal.getScoringSystem().goalPlayerOne();
                goal.getScoringSystem().checkScorePlayerOne();
                resetLeft(puck);
            }
        }
    }

    /**
     * Reset the paddles on the board to their initial positions.
     */
    public void resetPosition(Paddle paddle) {
        if (paddle.getPlayerType() == PlayerType.PLAYER1) {
            paddle.setX(1000f);
            paddle.setY(360f);
            paddle.setXspeed(0);
            paddle.setYspeed(0);
        } else {
            paddle.setX(360f);
            paddle.setY(360f);
            paddle.setXspeed(0);
            paddle.setYspeed(0);
        }
    }

    /**
     * Set the puck's position on the board to the initial one.
     */
    public void resetPuckPosition(Puck puck) {
        puck.setX(puck.getWidth() / 2);
        puck.setY(puck.getHeight() / 2);
        puck.setXspeed(0);
        puck.setYspeed(0);
    }

    public void resetLeft(Puck puck) {
        resetPuckPosition(puck);
        puck.setXspeed(50f);
    }

    public void resetRight(Puck puck) {
        resetPuckPosition(puck);
        puck.setXspeed(-50f);
    }

}
