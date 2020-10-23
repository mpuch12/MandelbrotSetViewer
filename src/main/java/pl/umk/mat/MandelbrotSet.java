package pl.umk.mat;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.concurrent.CountDownLatch;

public class MandelbrotSet extends Thread {


    public static WritableImage img = new WritableImage(1200,800);
    private   Color[] colors ;
    private int id;
    private int amountOfThreads;
    private int maxIterations;
    private CountDownLatch latch;
    private double xPosShift;
    private double yPosShift;
    private double zoom;


    public MandelbrotSet( int id, CountDownLatch latch,double xPosShift, double yPosShift, double zoom,int maxIterations, int amountOfThreads){
        this.id = id;
        this.latch = latch;
        this.xPosShift = xPosShift;
        this.yPosShift = yPosShift;
        this.zoom = zoom;
        this.maxIterations = maxIterations;
        this.amountOfThreads = amountOfThreads;
        colors = HSVtoRGB.createArrayOfColors(maxIterations+1);
    }


    @Override
    public void run() {
        int height = 800;
        int width = 1200;
        PixelWriter px = img.getPixelWriter();


        for (int k = 0; k < height; k++) {
            int row = k*amountOfThreads+id;
            if(row < height)
            for (int column = 0; column < width; column++) {
                double cRe = xPosShift + (column - width/2)*zoom;
                double cIm = yPosShift + (row - height/2)*zoom;

                double x = 0, y = 0;
                int i = 0;
                while (x*x+y*y < 4 && i < maxIterations) {
                    double xNew = x*x-y*y+cRe;
                    y = 2*x*y+cIm;
                    x = xNew;
                    i++;
                }
                double iteration = i;
                if (i < maxIterations){
                    double logI = Math.log(x*x+y*y)/2;
                    double n = Math.log(logI/Math.log(2))/Math.log(2);
                    iteration += 1- n;
                    if(iteration+1 >= maxIterations)
                        iteration-=1;

                    Color color = LinearInterpolation.calculate(colors[(int)Math.floor(iteration)], colors[(int)Math.floor(iteration+1)],iteration % 1);
                    px.setColor(column, row, color);
                }
                else
                    px.setColor(column, row, Color.BLACK);
            }
        }
        latch.countDown();
    }
}
