package gamelogic;
import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

public class Puck extends Collidable implements java.io.Serializable {

    public static final transient long serialVersionUID = 4328743;

    private transient EntityType entityType = EntityType.PUCK;


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
                float height) {
        super(x, y, radius, xspeed, yspeed, mass, width, height);

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
