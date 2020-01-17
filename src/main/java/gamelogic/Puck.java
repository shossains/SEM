package gamelogic;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

public class Puck extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient EntityType entityType = EntityType.PUCK;

    /**
     * The co-efficient of restitution.
     */
    private transient float puckWalle;

    private transient Sound sound;

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
                float height, float e, Sound sound) {
        super(x, y, radius, xspeed, yspeed, mass, width, height);

        this.puckWalle = e;

        this.sound = sound;
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
        //this.fixPosition();
    }

    @Override
    public void render(AirHockeyGame game, Texture texture) {

        game.spriteBatch.draw(texture, this.x - this.radius, this.y - this.radius,
                this.radius * 2, this.radius * 2);

    }

    @Override
    public EntityType getEntityType() {
        return this.entityType;
    }
}
