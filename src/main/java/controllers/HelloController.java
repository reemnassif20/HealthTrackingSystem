package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class HelloController {
    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    public void moveUp(ActionEvent e){
        myCircle.setCenterY(y-=10);

    }
    public void moveDown(ActionEvent e){
        myCircle.setCenterY(y+=10);

    }
    public void moveRight(ActionEvent e){
        myCircle.setCenterX(x+=10);

    }
    public void moveLeft(ActionEvent e){
        myCircle.setCenterX(x-=10);
    }




}