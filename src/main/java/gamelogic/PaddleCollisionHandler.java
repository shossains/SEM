package gamelogic;

import scoring.Board;

public class PaddleCollisionHandler {

    /**
     * Method to collide the entities and cast them to the appropriate types.
     * @param e1 Entity 1
     * @param e2 Entity 2
     */
    public void collide(Entity e1, Entity e2) {
        fixPaddlePosition((Paddle) e1, (Board) e2);
    }

    /**
     * Method to fix x and y position of the paddle.
     * @param paddle The paddle.
     * @param board The puck.
     */
    public void fixPaddlePosition(Paddle paddle, Board board) {
        fixPaddleXposition(paddle, board);
        fixPaddleYPosition(paddle, board);
    }

    /**
     * Method to fix the position of the paddle in the x axis if it is out of bounds.
     * @param paddle The paddle.
     * @param board The board with which it can collide.
     */
    public void fixPaddleXposition(Paddle paddle, Board board) {

        if (paddle.x - paddle.radius < paddle.xlower) {
            paddle.x = paddle.xlower + paddle.radius;
        }
        if (paddle.x > paddle.xupper - paddle.radius) {
            paddle.x = paddle.xupper - paddle.radius;
        }

    }

    /**
     * Method to fix the position of the puck in the y axis if it is out of bounds.
     * @param paddle The puck.
     * @param board The board with which it can collide.
     */
    public void fixPaddleYPosition(Paddle paddle, Board board) {
        if (paddle.y - paddle.radius < 0) {
            paddle.y = 0 + paddle.radius;
        }
        if (paddle.y > board.height - paddle.radius) {
            paddle.y = board.height - paddle.radius;
        }
    }
}
