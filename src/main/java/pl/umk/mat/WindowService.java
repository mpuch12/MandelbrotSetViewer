package pl.umk.mat;

import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;

public class WindowService {
    private WindowController windowController;
    private double xPosShift;
    private double yPosShift;
    private double zoom;
    private int maxIterations;
    private int amountOfThreads;
    private int shift;
    private double scale;

    public WindowService(WindowController windowController){
        this.windowController = windowController;
        zoom = 0.005;
    }

    public void calculateZoom(MouseEvent mouseEvent){
        getValuesFromFields();

        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            double oldZoom = zoom;
            zoom /= scale;
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            if(x > 600 && y >400){
                xPosShift += -(600-x)*(oldZoom) ;
                yPosShift += -(400-y)*(oldZoom) ;
            }else  if(x > 600 && y <400){
                xPosShift += -(600-x)*(oldZoom) ;
                yPosShift += (y-400)*(oldZoom) ;
            }else  if(x < 600 && y <400){
                xPosShift += (x-600)*(oldZoom) ;
                yPosShift += (y-400)*(oldZoom) ;
            }else  if(x <600 && y > 400){
                xPosShift += (x-600)*(oldZoom) ;
                yPosShift += -(400-y)*(oldZoom) ;
            }


        }
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            zoom *= scale;
        }

        setImage();
    }

    public void calculateImageShift(KeyEvent keyEvent){
        getValuesFromFields();

        if (keyEvent.getCode() == KeyCode.W )
            yPosShift -= shift*zoom;
        if (keyEvent.getCode() == KeyCode.S )
            yPosShift += shift*zoom;
        if (keyEvent.getCode() == KeyCode.A )
            xPosShift -= shift*zoom;
        if (keyEvent.getCode() == KeyCode.D )
            xPosShift += shift*zoom;

        setImage();
    }

    private void getValuesFromFields() {
        try {
            maxIterations = Integer.parseInt(windowController.getMaxIterationsField().getText());
            amountOfThreads = Integer.parseInt(windowController.getAmountOfThreadsField().getText());
            shift = Integer.parseInt(windowController.getShiftField().getText());
            if(maxIterations <= 0 || amountOfThreads <= 0){
                throw new ArithmeticException();
            }

        }catch (ArithmeticException e){
            windowController.createAlert("MINUS");
        }
        catch (Exception e) {
            windowController.createAlert("INTEGER");
        }

        try{
            scale = Double.parseDouble(windowController.getZoomFiled().getText());
        }catch (Exception e){
            windowController.createAlert("DOUBLE");
        }
    }

    public void setImage(){
        getValuesFromFields();
        windowController.setImageView(MandelbrotSetGenerator.create(xPosShift, yPosShift, zoom, maxIterations, amountOfThreads));
    }

    public void resetPosValue(){
        xPosShift = 0;
        yPosShift = 0;
        zoom = 0.005;
    }



}
