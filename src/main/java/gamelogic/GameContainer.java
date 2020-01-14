package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;

import java.util.ArrayList;

public class GameContainer {

    public transient ArrayList<Entity> entities;

    public transient ArrayList<Texture> textures;

    public GameContainer(ArrayList<Entity> entities, ArrayList<Texture> textures) {
        this.entities = entities;
        this.textures = textures;
    }

    public void update(float delta) {

        for (int i = 0; i < entities.size(); i++ ){
            entities.get(i).update(delta);
        }

    }

    public void render(AirHockeyGame game) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(game, textures.get(i));
        }
    }

    //sound effect stuff in here
    
}
