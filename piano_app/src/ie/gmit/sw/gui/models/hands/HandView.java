package ie.gmit.sw.gui.models.hands;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class HandView {

    // this is returned to a root
    private List<Node> hand_view;

    // represents a palm location
    private Circle palm;

    public Circle getPalm() {
        return palm;
    }

    // represents fingers location
    private List<FingerView> fingers;

    // properties for palm positioning
    private final DoubleProperty centerY =
            new SimpleDoubleProperty(0);
    private final DoubleProperty centerX =
            new SimpleDoubleProperty(0);
    private final DoubleProperty radius =
            new SimpleDoubleProperty(6);


    public HandView(String hand_id) {

        hand_view = new ArrayList<>();
        // create palm indicator
        palm = new Circle(6);
        palm.setFill(Color.RED);

        // bind custom properties that will be changed
        // based on Leap Listener data
        palm.translateXProperty().bind(centerX);
        palm.translateYProperty().bind(centerY);

        palm.radiusProperty().bind(radius);

        // create 5 fingers
        fingers = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            fingers.add(new FingerView());
        }

        // add elements to a group that represent a hand

        hand_view.add(palm);

        fingers.forEach(finger -> hand_view.add(finger.getFinger()));


    }

    // returns full hand
    public List<Node> getHand_view() {
        return hand_view;
    }

    public List<FingerView> getFingers() {
        return fingers;
    }

    // those methods are called by Leap Listener to update properties
    // of FX nodes
    public DoubleProperty centerX() {
        return centerX;
    }

    public DoubleProperty centerY() {
        return centerY;
    }

    public double getLocationX(){
        return palm.getLayoutX();
    }

    public double getLocationY(){
        return palm.getLayoutY();
    }

    public DoubleProperty radius () {
        return radius;
    }
}
