/**
 * A container for the full AimBot application including the engine and the visual representation.
 */
package main;

import main.engine.AimEngine;
import main.renderer.AimFrame;

public class AimBot {
    private AimEngine engine;
    private AimFrame frame;

    /**
     * Instantiate an AimBot that hopefully works!
     */
    public AimBot() {
        engine = new AimEngine();
        frame = new AimFrame(engine);
    }

}
