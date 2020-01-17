package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;
import java.util.ArrayList;
import scoring.ScoringSystem;

public class LocalGameContainer implements GameContainer{

    private transient CollisionsEngine collisionsEngine;

    public transient ArrayList<Entity> entities;

    public transient ArrayList<Texture> textures;

    public transient ScoringSystem scoringSystem;

    /**
     * A Constructor for the GameContainer Object.
     * The GameContainer holds all the interactive elements of the board.
     * The GameContainer handles updating, colliding and rendering of these objects.
     * @param entities An ArrayList of entities present on the board during the game.
     * @param textures An ArrayList of Textures of objects present on the board during the game.
     * @param scoringSystem The ScoringSystem used during the game.
     */
    public LocalGameContainer(ArrayList<Entity> entities, ArrayList<Texture> textures,
                              ScoringSystem scoringSystem, CollisionsEngine collisionsEngine) {
        this.entities = entities;
        this.textures = textures;
        this.scoringSystem = scoringSystem;
        this.collisionsEngine = collisionsEngine;
    }

    /**
     * Method to update the positions of the Entities and status of the game.
     * The status of the game is checked with ScoringSystem functionality.
     * It checks if the timer for the game has run out and if one of the players won the game.
     * @param delta the time that has passed since the last frame.
     */
    public void update(float delta) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(delta);
        }
        this.scoringSystem.checkTime();
        this.scoringSystem.checkScorePlayerOne();
        this.scoringSystem.checkScorePlayerTwo();
    }

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

    public void updateAfterGoal() {

        //check if a goal has been scored
        if (scoringSystem.justScored) {
            for (int i = 0; i < entities.size(); i++) {
                Entity ei = entities.get(i);

                if(ei.getEntityType() == EntityType.PADDLE) {
                    collisionsEngine.resetPosition((Paddle) ei);
                }
            }

            scoringSystem.justScored = false;
        }

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
}
