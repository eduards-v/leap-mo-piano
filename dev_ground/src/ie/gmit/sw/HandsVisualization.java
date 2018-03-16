package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

public class HandsVisualization {

    private List<UI_Finger> fingers;

    public HandsVisualization() {

        fingers = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            UI_Finger finger = new UI_Finger();
            fingers.add(finger);
        }
    }

    public List<UI_Finger> getFingers() {
        return fingers;
    }
}
