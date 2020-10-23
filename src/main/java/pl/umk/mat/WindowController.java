package pl.umk.mat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;



public class WindowController {

    /*public static double xPosShift = 0;
    public static double yPosShift = 0;
    public static double zoom = 0.005;
    public static int maxIterations = 1000;
    private int shift;
    private double scale;
    private int amountOfThreads;*/



    @FXML
    private TextField amountOfThreadsField;



    @FXML
    private ImageView imageView;

    @FXML
    private TextField maxIterationsField;

    @FXML
    private TextField zoomFiled;

    @FXML
    private TextField shiftField;

    @FXML
    private Button refreshButton;

    @FXML
    private Button focusButton;

    @FXML
    private Button restartButton;

    public TextField getAmountOfThreadsField() {
        return amountOfThreadsField;
    }

    public TextField getMaxIterationsField() {
        return maxIterationsField;
    }

    public TextField getZoomFiled() {
        return zoomFiled;
    }

    public TextField getShiftField() {
        return shiftField;
    }

    public void setImageView(Image image) {
        imageView.setImage(image);
    }


    public void initialize(){
        WindowService windowService = new WindowService(this);
        setInitialValues();
        windowService.setImage();

        imageView.setFocusTraversable(true);



        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                windowService.calculateZoom(mouseEvent);
            }
        });

        imageView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                windowService.calculateImageShift(keyEvent);
            }
        });

        focusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageView.requestFocus();
            }
        });




        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                windowService.setImage();
            }
        });

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setInitialValues();
                windowService.resetPosValue();
                windowService.setImage();
            }
        });
    }


    public void createAlert(String type) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        if(type.equals("DOUBLE"))
            alert.setContentText("Wpisz liczbę zmiennoprzecinkową w polu 'Zoom'.");
        else if (type.equals("INTEGER"))
            alert.setContentText("Wpisz liczbę naturalną w polach 'Ilość wątków','Ilość iteracji' oraz całkowitą w polu 'Przesunięcie'");
        else if (type.equals("MINUS"))
            alert.setContentText("Wpisz liczbę naturalną w polach 'Ilość wątków','Ilość iteracji'");
        alert.showAndWait();
    }

    private void setInitialValues() {

        zoomFiled.setText("1.5");
        maxIterationsField.setText("1000");
        amountOfThreadsField.setText("2");
        shiftField.setText("10");
    }

}