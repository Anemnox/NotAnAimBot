package main.engine;

import javax.swing.*;
import java.awt.*;

public class AimManager {
    private boolean active;

    public AimManager() {
        active = false;

    }

    /**
     *  If the Aimbot is currently active, will calculate and attempt to update the mouse position
     *  towards a potential target.
     */
    public void updateAim() {
        //take picture
        //find contours
        //move mouse towards center of potential target
    }

    /**
     * Toggles the state of the aimbot.
     */
    public void toggleAimBot() {
        active = !active;
    }
}
