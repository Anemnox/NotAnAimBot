package main.renderer;

import javax.swing.*;
import java.awt.*;

public class AimFrame extends JFrame {

    public AimFrame() {
        super("Main App");
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setAlwaysOnTop(true);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        this.setVisible(true);
    }
}
