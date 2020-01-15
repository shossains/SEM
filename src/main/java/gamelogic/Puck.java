package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

public class Puck extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient EntityType entityType = EntityType.PUCK;

    //private transient Texture puckImage =
    // new Texture(Gdx.files.internal("assets/hockey-puck.png"));

    /**
     * The co-efficient of restitution.
     */
    private transient float puckWalle;

    /**
     * Constructor.
     * The Puck is what is used to actually play the game. It interacts with the paddles
     * (the paddles can move it), and it is what can go into the goals and increase the score.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param xspeed Speed in x direction.
     * @param yspeed Speed in y direction.
     * @param radius The radius.
     */
    public Puck(float x, float y, float xspeed, float yspeed, float radius, float mass, float width,
                float height, float e) {
        super(x, y, radius, xspeed, yspeed, mass, width, height);

        this.puckWalle = e;
    }

    /**
     * This method makes sure the paddle is in the correct X boundaries.
     * We check if the puck is outside of the board boundaries,
     * and if it has hit an edge we calculate the speed after the resulting collision.
     */
    public void fixXPosition() {
        if (this.x - this.radius < 0) {
            this.x = 0 + this.radius;
            this.setXspeed(- this.getXspeed() * puckWalle);
        }
        if (this.x > getWidth() - this.radius) {
            this.x = getWidth() - this.radius;
            this.setXspeed(- this.getXspeed() * puckWalle);
        }
    }

    /**
     * This method makes sure the paddle is in the correct Y boundaries.
     * We check if the puck is outside of the board boundaries,
     * and if it has hit an edge we calculate the speed after the resulting collision.
     */
    public void fixYPosition() {
        if (this.y - this.radius < 0) {
            this.y = 0 + this.radius;
            this.setYspeed(- this.getYspeed() * puckWalle);
        }
        if (this.y > getHeight() - this.radius) {
            this.y = getHeight() - this.radius;
            this.setYspeed(- this.getYspeed() * puckWalle);
        }
    }

    /**
     * Set the puck's position on the board to the initial one.
     */
    public void resetPosition() {
        this.setX(getWidth() / 2);
        this.setY(getHeight() / 2);
        this.setXspeed(0);
        this.setYspeed(0);
    }

    public void resetLeft() {
        this.resetPosition();
        this.setXspeed(50f);
    }

    public void resetRight() {
        this.resetPosition();
        this.setXspeed(-50f);
    }

    @Override
    public void update(float delta) {
        this.move(delta);
        this.fixPosition();
    }

    @Override
    public void render(AirHockeyGame game, Texture texture) {

        //todo check if only a spritebatch is needed

        game.spriteBatch.draw(texture, this.x - this.radius, this.y - this.radius,
                this.radius * 2, this.radius * 2);

    }

    @Override
    public EntityType getEntityType() {
        return this.entityType;
    }
}
