package gamelogic;

public class Direction {
    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;

    /**
     * Direction object. Keeps track of the direction the paddle should move in.
     * @param upPressed Whether up is pressed.
     * @param downPressed Whether down is pressed.
     * @param rightPressed Whether right is pressed.
     * @param leftPressed Whether left is pressed.
     */
    public Direction(boolean upPressed, boolean downPressed,
                     boolean rightPressed, boolean leftPressed) {
        this.upPressed = upPressed;
        this.downPressed = downPressed;
        this.rightPressed = rightPressed;
        this.leftPressed = leftPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }
}
