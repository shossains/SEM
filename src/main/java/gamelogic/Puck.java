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
     * @param builder PuckBuilder used to create a Puck object.
     */
    public Puck(PuckBuilder builder) {
        super(builder.x, builder.y, builder.radius, builder.xspeed, builder.yspeed, builder.mass, builder.width, builder.height);

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

    public static class PuckBuilder {

        protected transient float x;
        protected transient float y;
        protected transient float xspeed;
        protected transient float yspeed;
        protected transient float radius;
        protected transient float mass;
        protected transient float width;
        protected transient float height;

        public PuckBuilder atX(float x) {
            this.x = x;
            return this;
        }

        public PuckBuilder atY(float y) {
            this.y = y;
            return this;
        }

        public PuckBuilder withSpeedX(float speedX) {
            this.xspeed = speedX;
            return this;
        }

        public PuckBuilder withSpeedY(float speedY) {
            this.yspeed = speedY;
            return this;
        }

        public PuckBuilder withRadius(float radius) {
            this.radius = radius;
            return this;
        }

        public PuckBuilder withMass(float mass) {
            this.mass = mass;
            return this;
        }

        public PuckBuilder onWidth(float width) {
            this.width = width;
            return this;
        }

        public PuckBuilder onHeight(float height) {
            this.height = height;
            return this;
        }

        public Puck build() {
            return new Puck(this);
        }

    }


}


