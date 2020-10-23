package pl.umk.mat;

import javafx.scene.paint.Color;

public class LinearInterpolation {
    public static Color calculate(Color color1, Color color2, double t){
        int red = (int) (color1.getRed()*255*(1 - t) + color2.getRed()*255 * t);
        int green = (int) (color1.getGreen()*255*(1 - t) + color2.getGreen()*255 * t);
        int blue = (int) (color1.getBlue()*255*(1 - t) + color2.getBlue()*255 * t);
        return Color.rgb( red,  green, blue);
    }
}
