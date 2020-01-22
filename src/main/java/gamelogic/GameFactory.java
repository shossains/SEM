package gamelogic;

import com.badlogic.gdx.audio.Sound;
import gui.GameScreen;
import scoring.Hud;

public abstract class GameFactory {

    public abstract GameContainer createGameContainer(Sound scoreSound,
                                                      Sound collisionSound,
                                                      GameScreen screen,
                                                      Hud hud);
}
