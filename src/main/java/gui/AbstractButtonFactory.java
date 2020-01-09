package gui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * The purpose of this interface is to create different kinds
 * of button factories and give them some functionality.
 * This interface is used by the graphical user interface.
 */
public interface AbstractButtonFactory {

    Button createButton(String string);

    Button createTransButton(String string, String newScreen);


}
