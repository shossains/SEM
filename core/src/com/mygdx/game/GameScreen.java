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
        game.spriteBatch.draw(puckImage, puck.x, puck.y, puck.radius*2, puck.radius*2);

        game.spriteBatch.end();
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
