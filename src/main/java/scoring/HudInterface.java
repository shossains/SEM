package scoring;

public interface HudInterface {

    /**
     * Update the time displayed by the HUD.
     * @param deltaTime the difference in numbers of seconds.
     */
    void updateTime(float deltaTime);

    /**
     * Give certain amount of points to PLayerOne.
     */
    void addScoreOne();

    /**
     * Give certain amount of points to PLayerTwo.
     */
    void addScoreTwo();

    /**
     * Get the remaining time for the current game.
     * The time that's displayed by the HUD.
     * @return the amount of seconds left for the game.
     */
    int getGameTimer();

    /**
     * Getter for the score of PlayerOne.
     * @return The amount of points gathered by PLayerOne.
     */
    int getScoreOne();

    /**
     * Getter for the score of PlayerTwo.
     * @return The amount of points gathered by PLayerTwo.
     */
    int getScoreTwo();


}
