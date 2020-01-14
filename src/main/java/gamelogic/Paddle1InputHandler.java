package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Paddle1InputHandler implements InputHandler {
    @Override
    public Direction handleInput() {
        boolean rightPressed1 = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean leftPressed1 = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean upPressed1 = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed1 = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        return new Direction(upPressed1, downPressed1, rightPressed1, leftPressed1);
    }
}
