package ie.gmit.sw;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UI_Finger{

    private final DoubleProperty centerY =
            new SimpleDoubleProperty(0);
    private final DoubleProperty centerX =
            new SimpleDoubleProperty(0);
    private final DoubleProperty radius =
            new SimpleDoubleProperty(4);

    private Circle finger;

    public UI_Finger() {
        finger = new Circle(2);
        finger.setFill(Color.GREEN);

        finger.translateXProperty().bind(centerX);
        finger.translateYProperty().bind(centerY);
        finger.radiusProperty().bind(radius);
    }

    public DoubleProperty centerX() {
        return centerX;
    }

    public DoubleProperty centerY() {
        return centerY;
    }

    public DoubleProperty radius () {
        return radius;
    }

    public Circle getFinger() {
        return finger;
    }
}