package ie.gmit.sw;

import com.leapmotion.leap.*;
import javafx.application.Platform;


public class LeapListener extends Listener{

    private HandsVisualization hands_ui;

    public LeapListener(HandsVisualization hands_ui) {
        this.hands_ui = hands_ui;
    }

    @Override
    public void onConnect(Controller controller) {

        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    @Override
    public void onFrame(Controller controller) {

        int ctr = 0;
        Frame frame = controller.frame();


        HandList hands = frame.hands();
        if (!hands.isEmpty()) {

            for(Hand hand : hands){

//                float x_h = hand.palmPosition().getX();
//                float z_h = hand.palmPosition().getZ();
//                ctr++;
//                UpdateFingersUI(x_h, z_h, ctr);

                for(Finger finger : hand.fingers()){
                    float x = finger.tipPosition().getX();
                    float z = finger.tipPosition().getZ();
                    UpdateFingersUI(x, z, ctr);
                    ctr ++;

                    System.out.println("Fingers counter: " + ctr);
                }

            }

        }
    }

    private void UpdateFingersUI(float x, float z, int ctr){

        Platform.runLater(() -> {
            FingerView _fingerView = hands_ui.getFingers().get(ctr);
            _fingerView.centerX().set(x);
            _fingerView.centerY().set(z);
        });
    }


/*
* hand.fingers().forEach(finger -> {
                float x = finger.tipPosition().getX();
                float y = finger.tipPosition().getY();
                float z = finger.tipPosition().getZ();
                UpdateFingersUI(x,y,z);
            })
**/

}
