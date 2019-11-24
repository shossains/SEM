package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MyGdxGame extends Game {


	public SpriteBatch spriteBatch;
	public BitmapFont font;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		font.dispose();
	}
}
