package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		myTexture = new Texture(Gdx.files.internal("pink.jpg"));
		myTextureRegion = new TextureRegion(myTexture);
		myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		button = new ImageButton(myTexRegionDrawable); //Set the button up
		button.setHeight(100);
		button.setWidth(500);
		button.setPosition(200, 200);

		button.addListener(
			new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					System.out.print("button pressed");
					outputLabel.setText("pressed");
				};
		});
		stage.addActor(button);
		outputLabel = new Label("label ",new Label.LabelStyle(new BitmapFont(),Color.BLUE));
		outputLabel.setText("touch the flower");
		outputLabel.setPosition(200, 200);
		button.add(outputLabel).expand().fill();
		stage.addActor(outputLabel);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)0.2, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();

	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
