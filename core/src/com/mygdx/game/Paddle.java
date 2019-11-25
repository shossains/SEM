package com.mygdx.game;

import com.badlogic.gdx.math.Circle;

public class Paddle extends Circle {

    public float xSpeed, ySpeed;

    /**
     * Constructor
     * @param x
     * @param y
     * @param xSpeed
     * @param ySpeed
     * @param radius
     */
    public Paddle(float x, float y, float xSpeed, float ySpeed, float radius) {

        super(x, y, radius);

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * Method to move the paddle, this can be expanded later to be a more complex and realistic
     * @param rightPressed
     * @param leftPressed
     * @param upPressed
     * @param downPressed
     * @param deltaTime
     */
    public void movePaddle(boolean rightPressed, boolean leftPressed,
                           boolean upPressed, boolean downPressed,
                           float deltaTime) {
        if (rightPressed) {
            this.x += 30*deltaTime;
        }

        if (leftPressed) {
            this.x -= 30*deltaTime;
        }

        if (upPressed) {
            this.y += 30*deltaTime;
        }

        if (downPressed) {
            this.y -= 30*deltaTime;
        }

    }

    /**
     * Method to ensure the puck is within the correct boundaries
     */
    public void FixPosition() {

        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
        }
        if (this.x > 400 - this.radius) {
            this.x = 400 - this.radius;
        }

        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
        }
        if (this.y > 300 - this.radius) {
            this.y = 300 - this.radius;
        }
    }


}
