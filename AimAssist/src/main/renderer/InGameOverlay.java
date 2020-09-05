/**
 * JPanel that provides information on the aimbot.
 */

package main.renderer;

import main.engine.AimEngine;

import javax.swing.*;
import java.awt.*;

public class InGameOverlay extends JPanel {
    private static final Color DEFAULT_BACKGROUND = new Color(100, 0, 100, 100);

    private AimEngine engine;
    private AimFrame frame;

    private Button actionButton;
    private Button takePhotoButton;

    public InGameOverlay(AimFrame frame, AimEngine engine) {
        this.engine = engine;
        this.frame = frame;

        this.setSize(90, 130);
        this.setBackground(DEFAULT_BACKGROUND);
        this.setVisible(true);

        actionButton = new Button(new Dimension(50, 20), 10, 60);
        actionButton.text = "Aim!";

        actionButton.setAction(() -> {
            engine.updateAim();
        });

        takePhotoButton = new Button(new Dimension(50, 20), 10, 85);
        takePhotoButton.text = "Photo";

        takePhotoButton.setAction(() -> {
            engine.takePhoto();
        });

        this.add(takePhotoButton);
        this.add(actionButton);

        repaint();
    }

    /**
     * Updates the component; such as repainting.
     */
    public void updateComp() {
        repaint();
    }

    /**
     * Paints the UI onto the given graphics object.
     * @param g graphics object to paint the overlay onto.
     */
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.drawString("FPS: " + frame.getFrameRate(), 10, 30);
        g.drawString("UPS: " + engine.getFrameRate(), 10, 50);
    }
}
