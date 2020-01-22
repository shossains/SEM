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
    void modifyScoreOne(int amount);

    /**
     * Give certain amount of points to PLayerTwo.
     */
    void modifyScoreTwo(int amount);

    /**
     * Get the remaining time for the current game.
     * The time that's displayed by the HUD.
     * @return the amount of seconds left for the game.
     */
    int getGameTimer();
}
