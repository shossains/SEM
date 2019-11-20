package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends Game {

	private Stage stage;
	private Texture myTexture;
	private TextureRegion myTextureRegion;
	private TextureRegionDrawable myTexRegionDrawable;
	private ImageButton button;
	private Label outputLabel;
	private SpriteBatch spriteBatch;
	private BitmapFont font;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		ImageButton button = createButton("login.png");
		ImageButton button2 = createButton("register.png");
		button.setPosition(300, 200);
		button2.setPosition(300, 100);
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		stage.addActor(button);
		stage.addActor(button2);
		outputLabel = new Label("label ",new Label.LabelStyle(new BitmapFont(),Color.BLUE));
		outputLabel.setText("Please log in before playing the game.");
		outputLabel.setPosition(200, 400);
		button.add(outputLabel).expand().fill();
		stage.addActor(outputLabel);
	}

	public ImageButton createButton(String path) {
		myTexture = new Texture(Gdx.files.internal(path));
		myTextureRegion = new TextureRegion(myTexture);
		myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		button = new ImageButton(myTexRegionDrawable); //Set the button up
		button.setHeight(100);
		button.setWidth(200);
		button.addListener(
				new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.print("button pressed");
						outputLabel.setText("pressed");
					};
				});
		return button;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)204/255, (float)204/255, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		stage.act();
		font.draw(spriteBatch, "Hello", 200, 200);
		stage.draw();
		spriteBatch.end();
	}

	@Override
	public void dispose () {
		stage.dispose();
		spriteBatch.dispose();
		font.dispose();
	}
}
