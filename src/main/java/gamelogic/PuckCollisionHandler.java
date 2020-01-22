package gamelogic;

import com.badlogic.gdx.audio.Sound;
import scoring.Board;

public class PuckCollisionHandler {

    private transient Sound sound;

    private transient float puckWalle;

    public PuckCollisionHandler(Sound sound, float puckWalle) {
        this.sound = sound;
        this.puckWalle = puckWalle;
    }

    /**
     * Method to fix x and y position of the puck and change its speed if necessary.
     * @param puck The puck.
     * @param board The board.
     */
    public void fixPuckPosition(Puck puck, Board board) {
        fixPuckXPosition(puck, board);
        fixPuckYPosition(puck, board);
    }

    /**
     * Method to fix the position of the puck in the x axis if it is out of bounds.
     * @param puck The puck.
     * @param board The board with which it can collide.
     */
    public void fixPuckXPosition(Puck puck, Board board) {
        if (puck.x - puck.radius < 0) {
            puck.x = 0 + puck.radius;
            puck.setXspeed(- puck.getXspeed() * puckWalle);
            sound.play();
        }
        if (puck.x > board.width - puck.radius) {
            puck.x = board.width - puck.radius;
            puck.setXspeed(- puck.getXspeed() * puckWalle);
            sound.play();
        }
    }

    /**
     * Method to fix the position of the puck in the y axis if it is out of bounds.
     * @param puck The puck.
     * @param board The board with which it can collide.
     */
    public void fixPuckYPosition(Puck puck, Board board) {
        if (puck.y - puck.radius < 0) {
            puck.y = 0 + puck.radius;
            puck.setYspeed(- puck.getYspeed() * puckWalle);
            sound.play();
        }
        if (puck.y > board.height - puck.radius) {
            puck.y = board.height - puck.radius;
            puck.setYspeed(- puck.getYspeed() * puckWalle);
            sound.play();
        }
    }
}
