package gamelogic;

import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;
import scoring.BasicScoringSystem;
import scoring.ScoringSystem;

import java.util.ArrayList;

public class GameContainer {

    private transient CollisionsEngine collisionsEngine = new CollisionsEngine(0.8f);

    public transient ArrayList<Entity> entities;

    public transient ArrayList<Texture> textures;

    public transient ScoringSystem scoringSystem;

    public GameContainer(ArrayList<Entity> entities, ArrayList<Texture> textures, ScoringSystem scoringSystem) {
        this.entities = entities;
        this.textures = textures;
        this.scoringSystem = scoringSystem;
    }

    /**
     * Method to update the positions of the Entities.
     * @param delta the time that has passed since the last frame.
     */
    public void update(float delta) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(delta);
        }
        this.scoringSystem.checkTime();
       // System.out.println("TIME CHECKED");
        this.scoringSystem.checkScorePlayerOne();
        //System.out.println("SCORE PLAYER ONE CHECKED");
        this.scoringSystem.checkScorePlayerTwo();
        //System.out.println("SCORE PLAYER TWO CHECKED");

    }

    /**
     * Method to render the Entities.
     * @param game The AirHockey game object.
     */
    public void render(AirHockeyGame game) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(game, textures.get(i));
        }
    }

    //sound effect stuff in here

    /**
     * The method that runs the collisions engine collide on the entities.
     */
    public void collideEntities() {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                collisionsEngine.collideEntities(entities.get(i), entities.get(j));
            }
        }
    }
}
