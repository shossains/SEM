package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class GameScreen implements Screen {
    final MyGdxGame game;

    //initially we need a texture for the paddle
    Texture puckImage;

    Texture paddle1Image;
    Texture paddle2Image;

    //we can use the circle class for the puck
    Puck puck;

    Paddle paddle1, paddle2;

    OrthographicCamera camera;

    boolean initMove = true;

    public GameScreen(final MyGdxGame game) {
        this.game = game;

        puckImage = new Texture(Gdx.files.internal("hockey-puck.png"));

        paddle1Image = new Texture(Gdx.files.internal("bluePaddle.png"));
        paddle2Image = new Texture(Gdx.files.internal("redPaddle.png"));

        camera = new OrthographicCamera();
        //we can change the resolution to whatever is appropriate later
        camera.setToOrtho(false, 400, 300);

        //we should later change it to the res0lution and so on...
        puck = new Puck(200f, 150f, 0f, 30f, 15f);

        //add the pucks
        paddle1 = new Paddle(100f, 150f, 0f, 0f, 20f);

        paddle2 = new Paddle(300f, 150f, 0f, 0f, 25f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //background colour
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update the camera
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.spriteBatch.setProjectionMatrix(camera.combined);

        game.spriteBatch.begin();

        //draw the puck as the texture and in the place that the puck exists
        //Maybe there is some border, or the radius doesn't perfectly scale up the image
        //the boundary is still not totally correct
        game.spriteBatch.draw(puckImage, puck.x - puck.radius, puck.y-puck.radius,
                puck.radius*2, puck.radius*2);

        game.spriteBatch.draw(paddle1Image, paddle1.x - paddle1.radius, paddle1.y - paddle1.radius,
                paddle1.radius*2, paddle1.radius*2);

        game.spriteBatch.draw(paddle2Image, paddle2.x - paddle2.radius, paddle2.y - paddle2.radius,
                paddle2.radius*2, paddle2.radius*2);

        game.spriteBatch.end();

        //move the puck

        float deltaTime = Gdx.graphics.getDeltaTime();

        puck.movePuck(deltaTime);
        //ensure it is within boundaries
        puck.FixPosition();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        puckImage.dispose();

    }
}
