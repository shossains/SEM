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
        game.spriteBatch.draw(puckImage, puck.x - puck.radius/2, puck.y-puck.radius/2, puck.radius, puck.radius);

        game.spriteBatch.end();

        //when the game starts, I want the puck only to move either up or down (random)
        //until it either hits a wall, or gets hit by a paddle
        //initially I will set the puck to go downwards
        if (initMove) {
            puck.y -= 15*Gdx.graphics.getDeltaTime();
        }
        //we need to add the functionality to check that if the puck has hit the boundaries
        //or has been hit by a paddle

        //boundary detection
        if (puck.x < 0) {
            puck.x = 0;
        }
        if (puck.x > 400 - puck.radius*2) {
            puck.x = 400 - puck.radius*2;
        }

        if (puck.y < 0) {
            puck.y = 0;
            //also set the initMove to false;
            initMove = false;
        }
        if (puck.y > 300 - puck.radius*2) {
            puck.y = 300 - puck.radius*2;
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
