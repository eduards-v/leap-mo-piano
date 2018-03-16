package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

public class HandsVisualization {

    private List<FingerView> fingers;

    public HandsVisualization() {

        fingers = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            FingerView finger = new FingerView();
            fingers.add(finger);
        }
    }

    public List<FingerView> getFingers() {
        return fingers;
    }
}
