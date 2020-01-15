package gui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class DialogFactory {

    transient AirHockeyGame game;
    transient Screen screen;
    transient TextFieldFactory textFieldFactory;

    /**
     * Constructor for the DialogFactory class.
     * @param game that is currently playing.
     * @param screen the screen that is currently showing.
     */
    public DialogFactory(AirHockeyGame game, Screen screen) {
        this.game = game;
        this.screen = screen;
        textFieldFactory = new TextFieldFactory(this.game, this.screen);
    }

    /**
     * Dialog that pops-up on this.screen
     * used in the GUI.
     * @param text1 the title of the Dialog.
     * @param text2 the problem explained.
     * @return a new Dialog object.
     */
    public Dialog createDialog(String text1, String text2) {
        Dialog dialog = new Dialog(text1,
                textFieldFactory.createSkin(),
                "dialog") {
        };
        dialog.setColor(Color.RED);
        dialog.setSize(400, 200);
        dialog.text(text2);
        dialog.button("Ok", false);
        return dialog;
    }
}
