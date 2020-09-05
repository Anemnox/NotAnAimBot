/**
 * A simple Button Component.
 */
package main.renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JPanel implements MouseListener {
    public static final Color DEFAULT_BACKGROUND = new Color(255, 255, 255, 255);

    private Color buttonColor;
    public String text;
    private ButtonAction action;

    /**
     * Given the dimensions and position of the button, will create a button with
     * the default color.
     * @param dim dimensions of the button
     * @param x x position of the button
     * @param y y position of the button
     */
    public Button(Dimension dim, int x, int y) {
        buttonColor = DEFAULT_BACKGROUND;
        this.setSize(dim);
        this.setLocation(x, y);
        this.setBackground(buttonColor);

        this.setVisible(true);

        this.addMouseListener(this);
    }

    public void setAction(ButtonAction action) {
        this.action = action;
    }

    public void paint(Graphics g) {
        super.paint(g);
        if(text != null) {
            g.drawString(text, 5, this.getSize().height - 3);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(action != null) {
            action.doAction();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(buttonColor.darker().darker());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(buttonColor.darker());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(buttonColor.darker());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(buttonColor);
    }
}
