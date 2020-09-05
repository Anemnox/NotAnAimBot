package main;

import java.awt.Robot;
import java.util.Random;

public class TestMouseMover {
    public static final int FIVE_SECONDS = 50;
    public static final int MAX_Y = 10;
    public static final int MAX_X = 10;
    public static int x = 500;
    public static int y = 500;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();
        while (true) {
            x += random.nextInt(MAX_X) - 5;
            y += random.nextInt(MAX_Y) - 5;
            robot.mouseMove(x, y);
            Thread.sleep(FIVE_SECONDS);
        }
    }
}
