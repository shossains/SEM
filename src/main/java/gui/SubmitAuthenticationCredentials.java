package gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import database.Adapter;
import gamelogic.CredentialsChecker;
import gamelogic.QueryGetter;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class SubmitAuthenticationCredentials implements SubmitCredentials {

    @Override
    public void submitCredentials(AirHockeyGame game, LoginScreen screen,
                                  String username, String password) {

        DialogFactory dialogFactory = new DialogFactory(game, screen);
        CredentialsChecker credentialsChecker = new CredentialsChecker(screen,
                new Adapter(), new QueryGetter());
        String response = credentialsChecker.checkLoginCredentials(username, password);

        switch (response) {
            case "empty" : {
                Dialog dialog = dialogFactory.createDialog("Empty fields",
                        "Please fill in all fields!");
                dialog.show(screen.stage);
                break;
            }

            case "correct": {
                MainMenuScreen m = new MainMenuScreen(game);
                m.username = username;
                ((Game) Gdx.app.getApplicationListener()).setScreen(m);
                break;
            }

            case "incorrect": {
                Dialog dialog = dialogFactory.createDialog("Incorrect credentials",
                        "Incorrect username and/or password");
                dialog.show(screen.stage);
                break;
            }
            default: {
                Dialog dialog = dialogFactory.createDialog("Error",
                        "Please try again");
                dialog.show(screen.stage);
                break;
            }
        }
    }

    @Override
    public void submitCredentials(AirHockeyGame game, RegistrationScreen screen,
                                  String username, String password,
                                  String email, String passwordAgain) {
        DialogFactory dialogFactory = new DialogFactory(game, screen);
        CredentialsChecker credentialsChecker = new CredentialsChecker(screen,
                new Adapter(), new QueryGetter());
        String result = credentialsChecker.checkRegisterCredentials(username, password,
                email, passwordAgain);

        switch (result) {
            case "empty": {
                Dialog dialog = dialogFactory.createDialog("Empty fields",
                        "Please fill in all fields!");
                dialog.show(screen.stage);
                break;
            }

            case "passwordsNotMatching": {
                Dialog dialog = dialogFactory.createDialog("Warning - wrong password",
                        "Please enter the password again."
                                + " Your passwords do not match.");
                dialog.show(screen.stage);
                break;
            }

            case "correct": {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new
                        MainMenuScreen(game));
                break;
            }

            case "incorrect": {
                Dialog dialog = dialogFactory.createDialog("Email or username already in use.",
                        "Please try again.");
                dialog.show(screen.stage);
                break;
            }

            default: {
                Dialog dialog = dialogFactory.createDialog("Error.",
                        "Please try again.");
                dialog.show(screen.stage);
                break;
            }
        }
    }
}
