package ie.gmit.sw;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class HandView {

    // this is returned to a root
    private Group hand_view;

    // represents a palm location
    private Circle palm;

    // represents fingers location
    private List<FingerView> fingers;

    // properties for palm positioning
    private final DoubleProperty centerY =
            new SimpleDoubleProperty(0);
    private final DoubleProperty centerX =
            new SimpleDoubleProperty(0);
    private final DoubleProperty radius =
            new SimpleDoubleProperty(6);


    // passing an id for the hand to find it in node graph, i.e., hand1 / hand2
    public HandView(String hand_id) {

        // create palm indicator
        palm = new Circle(6);
        palm.setFill(Color.AZURE);

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
        hand_view = new Group();
        hand_view.setId(hand_id); // will be used to find a hand in UI

        hand_view.getChildren().add(palm);

        fingers.forEach(finger -> hand_view.getChildren().add(finger.getFinger()));


    }

    // returns full hand
    public Node getHand_view() {
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

    public DoubleProperty radius () {
        return radius;
    }
}
