import GUI.MyGdxGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

    /**
     * The main function for the game.
     * @param arg argument for main function
     */
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Air hockey";
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new MyGdxGame(), config);
    }
}