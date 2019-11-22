package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class GameScreen implements Screen {
    final MyGdxGame game;

    //initially we need a texture for the paddle
    Texture puckImage;

    //we can use the circle class for the puck
    Circle puck;

    OrthographicCamera camera;

    boolean initMove = true;

    public GameScreen(final MyGdxGame game) {
        this.game = game;

        puckImage = new Texture(Gdx.files.internal("hockey-puck.png"));

        camera = new OrthographicCamera();
        //we can change the resolution to whatever is appropriate later
        camera.setToOrtho(false, 400, 300);

        puck = new Circle();
        //will have to play around with these settings
        puck.radius = 15;

        //Start it in the center of the screen
        puck.x = 200;
        puck.y = 150;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //background colour
        Gdx.gl.glClearColor(0.5f, 0.5f, 0, 1);
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
        game.spriteBatch.draw(puckImage, puck.x - puck.radius, puck.y-puck.radius, puck.radius*2, puck.radius*2);

        game.spriteBatch.end();

        //when the game starts, I want the puck only to move either up or down (random)
        //until it either hits a wall, or gets hit by a paddle
        //initially I will set the puck to go downwards
        if (initMove) {
            puck.y += 30*Gdx.graphics.getDeltaTime();
        }
        //we need to add the functionality to check that if the puck has hit the boundaries
        //or has been hit by a paddle

        //boundary detection
        //the puck.x and y represent the center of the circle
        if (puck.x - puck.radius < 0) {
            puck.x = 0 + puck.radius;
        }
        if (puck.x > 400 - puck.radius) {
            puck.x = 400 - puck.radius;
        }

        if (puck.y - puck.radius < 0) {
            puck.y = 0 + puck.radius;
            //also set the initMove to false;
            initMove = false;
        }
        if (puck.y > 300 - puck.radius) {
            puck.y = 300 - puck.radius;
        }
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
