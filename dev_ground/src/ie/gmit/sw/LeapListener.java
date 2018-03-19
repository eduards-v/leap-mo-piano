package ie.gmit.sw;

import com.leapmotion.leap.*;
import javafx.application.Platform;


public class LeapListener extends Listener{


    private ListenerDataHandler listenerDataHandler;

    public LeapListener() {
        this.listenerDataHandler = new ListenerDataHandler();
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

        listenerDataHandler.handleHandsData(hands);

    }

//    private void UpdateFingersUI(float x, float z, int ctr){
//
//        Platform.runLater(() -> {
//            FingerView _fingerView = hands_ui.getFingers().get(ctr);
//            _fingerView.centerX().set(x);
//            _fingerView.centerY().set(z);
//        });
//    }


/*
* hand.fingers().forEach(finger -> {
                float x = finger.tipPosition().getX();
                float y = finger.tipPosition().getY();
                float z = finger.tipPosition().getZ();
                UpdateFingersUI(x,y,z);
            })
**/

}
