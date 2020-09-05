package main.engine;
/**
 * Created by andrew on 9/25/17.
 */

public class GameLoop extends Thread {
    private int maxFPS;
    private boolean frameCapOn;
    public double averageMillisPerFrame;
    public int averageFPS;
    private boolean isRunning;

    long currentTime, previousTime;
    double millisPerTick;
    int totalFrames;
    double elapsedTime;
    double totalTime;
    RunOnGameLoop updateClass;

    //Constructors
    public GameLoop(RunOnGameLoop update, int maxFrames, boolean mode) {
        setMaxFPS(maxFrames);
        frameCapOn = mode;
        updateClass = update;
    }

    public GameLoop(RunOnGameLoop update, int maxFrames) {
        this(update, maxFrames, true);
    }

    public GameLoop(RunOnGameLoop update) {
        this(update, 100, true);
    }

    //
    //   Start Loop
    //
    public void run() {
        int ghosttick = 0;
        isRunning = true;
        averageFPS = 0;
        previousTime = System.nanoTime();
        totalTime = 0;
        totalFrames = 0;

        //System.out.println("System time: " + System.nanoTime());
        while(isRunning) {
            currentTime = System.nanoTime();
            elapsedTime = toMillis(currentTime - previousTime);
            previousTime = currentTime;
            totalTime += elapsedTime;

            if(Math.ceil(totalTime) >= 1000) {
                averageFPS = totalFrames;
                ghosttick = 0;
                totalTime -= 1000;
                totalFrames = 0;
                //System.out.println("Fps: " + averageFPS);
            }

            averageMillisPerFrame = toAverageMillisPerFrame(totalTime, totalFrames);
            if (frameCapOn) {
                if (totalFrames < maxFPS) {
                    if (totalTime <= (millisPerTick * totalFrames)) {
                        try {
                            tick(elapsedTime);

                            if ((millisPerTick * (totalFrames + 1)) - totalTime < millisPerTick) {
                                Thread.sleep((int) ((Math.ceil(millisPerTick - elapsedTime))));
                            } else {
                                Thread.sleep((int) Math.ceil(millisPerTick));
                            }
                        } catch (Exception ex) {

                        }
                    } else {
                        tick(elapsedTime);
                    }
                } else {
                    ghosttick += 1;
                    try {
                        Thread.sleep((int)((millisPerTick * totalFrames) - totalTime));
                    } catch (Exception e) {

                    }
                }
            } else {
                tick(elapsedTime);
            }


        }
        try {
            this.join();
        } catch(Exception exc) {

        }
    }

    //
    //   UPDATE Methods
    //
    
    private void tick(double updateIncrament) {
        totalFrames++;
        updateClass.update(updateIncrament);
    }

    //
    //   Loop Settings
    //
    public void togglePowerSavingMode() {
        if(frameCapOn) frameCapOn = false;
        else frameCapOn = true;
    }
    public void setMaxFPS(int maxFrames) {
        if(maxFrames > 1000) {
            maxFPS = 1000;
        }
        if(maxFrames > 20) {
            maxFPS = (maxFrames / 10) * 10;
        } else {
            maxFPS = 20;
            System.out.println("Can't be lower than 20 frames");
        }
        millisPerTick = 1000 / maxFPS;
    }
    public void stopGameLoop() {
        isRunning = false;
    }

    //
    //   Return Methods
    //
    public boolean isSavingPower() {
        return frameCapOn;
    }

    //
    //   Private Time Calculators
    //
    private double toMillis(long nanotime){
        return (double) nanotime / 1000000.00;
    }
    
    private double toAverageMillisPerFrame(double time, int totalFrames) {
        return (double) time / totalFrames;
    }
    private double toFrameRate(double time, int totalFrames) {
        return (double) totalFrames * (time/1000);
    }
}