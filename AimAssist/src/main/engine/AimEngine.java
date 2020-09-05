/**
 * The Brains and Muscle for the AimBot. The AimEngine will handle all of processes that will calculates and moves
 * the mouse according to the calculation.
 */

package main.engine;

import java.awt.image.BufferedImage;

public class AimEngine implements RunOnGameLoop {
    private boolean active;
    private GameLoop updateLoop;

    private PhotoCamera camera;

    /**
     * Main Object that handles the control and pipeline for our AimBot.
     */
    public AimEngine() {
        active = true;

        try {
            camera = new PhotoCamera();
            camera.centerCamera();
        } catch(Exception e) {
            e.printStackTrace();
        }
        updateLoop = new GameLoop(this, 30);
        updateLoop.start();
    }

    /**
     *  If the Aimbot is currently active, will calculate and attempt to update the mouse position
     *  towards a potential target.
     */
    public void updateAim() {
        if(camera != null) {
            BufferedImage image = camera.capturePhoto();
        }
        //take picture
        //find contours
        //move mouse towards center of potential target
    }

    public void takePhoto() {
        camera.savePhoto(camera.capturePhoto());
    }

    /**
     * Toggles the state of the aimbot.
     */
    public void toggleAimBot() {
        active = !active;
    }

    /**
     * Updates the engine by one tick
     * @param tick time in ms since the last update
     */
    @Override
    public void update(double tick) {
        if(active) {
            updateAim();
        }
    }

    /**
     * Returns the current update rate of the game engine.
     * @return current update rate
     */
    public int getFrameRate() {
        return updateLoop.averageFPS;
    }
}
