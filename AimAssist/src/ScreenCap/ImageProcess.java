package ScreenCap;

import org.opencv.core.Point;
import org.opencv.core.CvType;
import java.util.List;
import org.opencv.core.MatOfPoint;
import java.util.ArrayList;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Size;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import java.util.Random;

public class ImageProcess
{
    private Random rng;

    public ImageProcess() {
        this.rng = new Random(12345L);
    }

    public void openPicAndProcesses(final String filename) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        final Imgcodecs imageCodecs = new Imgcodecs();
        final Mat matrix = Imgcodecs.imread(filename);
        System.out.println("image opened");
        final Mat blurredImage = new Mat();
        final Mat hsvImage = new Mat();
        final Mat mask_red1 = new Mat();
        final Mat mask_red2 = new Mat();
        final Mat morphOutput = new Mat();
        Imgproc.blur(matrix, blurredImage, new Size(7.0, 7.0));
        Imgproc.cvtColor(blurredImage, hsvImage, 40);
        HighGui.imshow("blurredImage", blurredImage);
        HighGui.imshow("hsvImage", hsvImage);
        Scalar redLower = new Scalar(0.0, 84.15, 127.5);
        Scalar redUpper = new Scalar(10.0, 255.0, 178.5);
        Core.inRange(hsvImage, redLower, redUpper, mask_red1);
        redLower = new Scalar(170.0, 120.0, 70.0);
        redUpper = new Scalar(180.0, 255.0, 255.0);
        Core.inRange(hsvImage, redLower, redUpper, mask_red2);
        final Mat finalMask = new Mat();
        Core.add(mask_red1, mask_red2, finalMask);
        HighGui.imshow("mask1", mask_red1);
        HighGui.imshow("mask2", mask_red2);
        HighGui.imshow("mask1+2", finalMask);
        final int threshold = 100;
        final Mat cannyOutput = new Mat();
        Imgproc.Canny(finalMask, cannyOutput, (double)threshold, (double)(threshold * 2));
        final List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        final Mat hierarchy = new Mat();
        Imgproc.findContours(cannyOutput, (List)contours, hierarchy, 3, 2);
        final Mat drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3);
        for (int i = 0; i < contours.size(); ++i) {
            final Scalar color = new Scalar((double)this.rng.nextInt(256), (double)this.rng.nextInt(256), (double)this.rng.nextInt(256));
            Imgproc.drawContours(drawing, (List)contours, i, color, 2, -1, hierarchy, 0, new Point());
        }
        HighGui.imshow("drawing+2", drawing);
        HighGui.waitKey();
    }
}
