package gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import gui.AirHockeyGame;
import java.util.ArrayList;
import scoring.ScoringSystem;

public class GameContainer {

    private transient CollisionsEngine collisionsEngine = new CollisionsEngine(0.8f,
            Gdx.audio.newSound(Gdx.files.internal("assets/collide.wav")));

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
    public GameContainer(ArrayList<Entity> entities, ArrayList<Texture> textures,
                         ScoringSystem scoringSystem) {
        this.entities = entities;
        this.textures = textures;
        this.scoringSystem = scoringSystem;
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