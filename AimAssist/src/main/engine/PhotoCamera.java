/**
 * A simple Camera Object that will take a photo of your screen when you want!
 *
 */

package main.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PhotoCamera {
    public static final String CAPTURE_DIR = "src/main/images/";
    public static final String FILE_NAME = "PartialScreenshot";
    public static final String FORMAT = "jpg";
    public static final Dimension DEFAULT_DIM = new Dimension(500, 250);
    public static final Point DEFAULT_POINT = new Point(0, 0);

    private int xPos;
    private int yPos;
    private int photoWidth;
    private int photoHeight;
    private Robot robot;

    /**
     * Create a Camera with the given dimensions at the specific point
     * @param dim dimension of the camera.
     * @param point top left corner of camera.
     * @throws AWTException
     */
    public PhotoCamera(Dimension dim, Point point) throws AWTException {
        robot = new Robot();

        xPos = point.x;
        yPos = point.y;
        photoWidth = dim.width;
        photoHeight = dim.height;
    }

    /**
     * Instantiate camera with the default position and dimension.
     * @throws AWTException
     */
    public PhotoCamera() throws AWTException {
        this(DEFAULT_DIM, DEFAULT_POINT);
    }

    /**
     * Instantiate camera with the default position and given dimension.
     * @param dim dimension of the camera.
     * @throws AWTException
     */
    public PhotoCamera(Dimension dim) throws AWTException {
        this(dim, DEFAULT_POINT);
    }

    /**
     * Saves the given image to the image directory with the given
     * filename
     * @param image Image to save into image directory.
     * @param filename name of the image file.
     */
    public void savePhoto(BufferedImage image, String filename) {
        try {
            String fileName = CAPTURE_DIR + filename + "." + FORMAT;
            ImageIO.write(image, FORMAT, new File(fileName));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Saves image to the image directory with default filename
     * @param image Image to save into image directory.
     */
    public void savePhoto(BufferedImage image) {
        savePhoto(image, FILE_NAME);
    }

    /**
     * Returns a photo taken from the screen based on camera settings.
     * @return image of the screen.
     */
    public BufferedImage capturePhoto() {
        BufferedImage screenFullImage = null;
        String fileName = CAPTURE_DIR + FILE_NAME + "." + FORMAT;

        Rectangle captureRect = new Rectangle(xPos, yPos, photoWidth, photoHeight);
        screenFullImage = robot.createScreenCapture(captureRect);

        return screenFullImage;
    }

    /**
     * Centers the camera to the center of the screen.
     */
    public void centerCamera() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xPos = screenSize.width / 2 - photoWidth / 2;
        yPos = screenSize.height / 2 - photoHeight / 2;
    }
}
