package ScreenCap;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FullScreenCaptureExample {


    private static int xlim = 500;
    private static int ylim = 250;


    public FullScreenCaptureExample(int lim) {
        this(lim, lim);
    }
    
    public FullScreenCaptureExample(int xlim, int ylim) {
        this.xlim = xlim;
        this.ylim = ylim;
    }



    public static void main(String[] args) {
        try {

            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "PartialScreenshot." + format;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int xTopLeft = screenSize.width / 2 - xlim / 2;
            int yTopLeft = screenSize.height / 2 - ylim / 2;

            Rectangle captureRect = new Rectangle(xTopLeft, yTopLeft, xlim, ylim);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A partial screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }
}
