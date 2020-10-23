package pl.umk.mat;

import javafx.scene.image.Image;

import java.util.concurrent.CountDownLatch;

public class MandelbrotSetGenerator {

    public static Image create(double xPosShift, double yPosShift, double zoom,int maxIterations, int amountOfThreads) {
        MandelbrotSet[] set = new MandelbrotSet[amountOfThreads];
        CountDownLatch latch = new CountDownLatch(amountOfThreads);

        for (int i = 0; i < amountOfThreads; i++) {
            set[i] = new MandelbrotSet(i, latch, xPosShift, yPosShift, zoom, maxIterations, amountOfThreads);
        }

        double time = System.nanoTime();

        for (int i = 0; i < amountOfThreads; i++) {
            set[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        time = System.nanoTime() - time;

        System.out.println("Wygenerowano w " + time/1000000000 + "s.");



        return MandelbrotSet.img;
    }
}
