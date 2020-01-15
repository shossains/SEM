package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import database.Adapter;
import database.TopScores;
import database.UserScore;

/**
 * The purpose of this class is to create an graphical user interface
 * shown after the game has finished which disposes the top five results
 * registered in the database. This class makes use of gui.ButtonFactory and
 * gui.TextFieldFactory classes to create new objects.
 * The current user is asked to enter a nickname.
 * Then, the method checkScores is called which disposes
 * the top 5 scores ever registered.
 */
public class ScoresScreen implements Screen {

    final transient AirHockeyGame game;
    public transient Stage stage;
    final transient TextField usernameTextField;
    final transient TextFieldFactory textFieldFactory;
    final transient int score;

    /**
     * Constructor for GUI.Scores Screen
     * Here, a test field for nickname is created.
     * After the user enters his/her nickname, a pop-up
     * window shows top 5 scores.
     * After that, the user can press the exit button to go back
     * to the chooseGameScreen page.
     * @param game the current game.
     * @param score the score recieved from the GUI.GameScreen class.
     */
    public ScoresScreen(AirHockeyGame game, int score) {
        this.game = game;
        this.score = score;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        textFieldFactory = new TextFieldFactory(this.game, this);
        usernameTextField = textFieldFactory.createTextField();
        usernameTextField.setPosition(250,200);
        stage.addActor(usernameTextField);

        AbstractButtonFactory buttonFactory = new TextButtonFactory(this.game, this);
        Button button = buttonFactory.createButton("Done!");
        button.setPosition(100, 300);
        button.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        checkScore(score);
                    }
                });

        stage.addActor(button);

        Button exit = buttonFactory.createTransButton("Back", "ChooseGameScreen");
        exit.setPosition(900, 600);
        stage.addActor(exit);
    }

    /**
     * Method that shows the best scores.
     * This method makes use of the Query class.
     * @param score the score of the current player.
     */
    public void checkScore(int score) {
        String username = usernameTextField.getText();
        Adapter adapter = new Adapter();
        UserScore userScore = new UserScore(adapter.conn, username, score);
        userScore.execute(adapter.conn);
        TopScores topScores = new TopScores(adapter.conn);
        String scores = topScores.execute(adapter.conn);

        Dialog dialog = new Dialog("Top 5 scores",
                textFieldFactory.createSkin(),
                "dialog") {
        };
        dialog.setColor(Color.RED);
        dialog.setSize(600, 600);
        dialog.text(scores);
        dialog.button("Ok", false);
        dialog.show(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)1, (float)170 / 255, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.spriteBatch.begin();
        game.font.setColor(Color.ROYAL);
        game.font.draw(game.spriteBatch, "Please enter your nickname.", 250, 425);
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
