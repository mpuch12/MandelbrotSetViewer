package pl.umk.mat;

import javafx.scene.paint.Color;

public class HSVtoRGB {

    public static Color[] createArrayOfColors(int maxIterations){
        Color[] colors = new Color[maxIterations];
        for (int i = 0; i<maxIterations; i++) {
            int[] col = convert(i/256f, 1, i/(i+30f));
            colors[i] = Color.rgb(col[0], col[1], col[2]);
        }
        return colors;
    }

    private static int[] convert(float h, float s, float v){
        float m, n, f;
        int i;

        float[] hsv = new float[3];
        float[] rgb = new float[3];
        int[] RGB = new int[3];

        hsv[0] = h;
        hsv[1] = s;
        hsv[2] = v;

        if (hsv[0] == -1)
        {
            rgb[0] = rgb[1] = rgb[2] = hsv[2];
            RGB[0] = (int) (rgb[0] * 255.0f);
            RGB[1] = (int) (rgb[1] * 255.0f);
            RGB[2] = (int) (rgb[2] * 255.0f);
            return RGB;
        }
        i = (int) (Math.floor(hsv[0]));
        f = hsv[0] - i;
        if (i % 2 == 0)
        {
            f = 1 - f; // if i is even
        }
        m = hsv[2] * (1 - hsv[1]);
        n = hsv[2] * (1 - hsv[1] * f);
        switch (i)
        {
            case 6:
            case 0:
                rgb[0] = hsv[2];
                rgb[1] = n;
                rgb[2] = m;
                break;
            case 1:
                rgb[0] = n;
                rgb[1] = hsv[2];
                rgb[2] = m;
                break;
            case 2:
                rgb[0] = m;
                rgb[1] = hsv[2];
                rgb[2] = n;
                break;
            case 3:
                rgb[0] = m;
                rgb[1] = n;
                rgb[2] = hsv[2];
                break;
            case 4:
                rgb[0] = n;
                rgb[1] = m;
                rgb[2] = hsv[2];
                break;
            case 5:
                rgb[0] = hsv[2];
                rgb[1] = m;
                rgb[2] = n;
                break;
        }

        RGB[0] = (int) (rgb[0] * 255.0f);
        RGB[1] = (int) (rgb[1] * 255.0f);
        RGB[2] = (int) (rgb[2] * 255.0f);

        return RGB;


    }
}
