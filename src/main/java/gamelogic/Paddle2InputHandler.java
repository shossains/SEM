package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Paddle2InputHandler implements InputHandler {

    @Override
    public Direction handleInput() {
        boolean rightPressed2 = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean leftPressed2 = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean upPressed2 = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean downPressed2 = Gdx.input.isKeyPressed(Input.Keys.S);
        return new Direction(upPressed2, downPressed2, rightPressed2, leftPressed2);
    }
}
