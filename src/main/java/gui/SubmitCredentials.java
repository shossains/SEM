package gui;

public interface SubmitCredentials {

    void submitCredentials(AirHockeyGame game, LoginScreen screen,
                           String username, String password);

    void submitCredentials(AirHockeyGame game, RegistrationScreen screen,
                           String username, String password, String email, String passwordAgain);
}
