/**
 * The GUI for the aimbot. Provides visuals to the status of the aimbot.
 */

package main.renderer;

import main.engine.AimEngine;
import main.engine.GameLoop;
import main.engine.RunOnGameLoop;

import javax.swing.*;
import java.awt.*;

public class AimFrame extends JFrame implements RunOnGameLoop {
    public static final int WIDTH = 90;
    public static final int HEIGHT = 130;

    private AimEngine engine;
    private InGameOverlay overlay;
    private GameLoop graphicsLoop;

    /**
     * A visual representation for the AimEngine including an overlay for the application.
     * @param engine the AimEngine to represent.
     */
    public AimFrame(AimEngine engine) {
        super("Main App");

        this.setSize(WIDTH, HEIGHT);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setAlwaysOnTop(true);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        this.setFocusable(false);
        this.setVisible(true);
        this.setFocusableWindowState(false);

        this.engine = engine;

        overlay = new InGameOverlay(this, engine);
        this.getContentPane().add(overlay);

        graphicsLoop = new GameLoop(this, 30);
        graphicsLoop.start();
    }

    /**
     * Updates the Graphics UI such as repainting the UI and etc.
     * @param tick time in ms since last update
     */
    @Override
    public void update(double tick) {
        overlay.updateComp();
    }

    /**
     * Returns the current Frame rate of the UI
     * @return
     */
    public int getFrameRate() {
        return graphicsLoop.averageFPS;
    }
}
