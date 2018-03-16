package ie.gmit.sw;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import javafx.application.Platform;

import java.util.List;

public class ListenerDataHandler {

    private HandsViewController handsViewController;

    public ListenerDataHandler() {
        handsViewController = HandsViewController.getInstance();
    }

    public void handleHandsData(HandList hands){

        if(!hands.isEmpty()){
            switch (hands.count()){
                case 1:
                       Hand hand = hands.get(0);
                       if(hand.isRight()){
                           HandView handView = handsViewController.getHands().get("hand_r");

                           Platform.runLater(() -> {
                               handlePalmPosition(hand, handView);
                               handleFingersPosition(hand, handView);
                           });
                       } else{
                           HandView handView = handsViewController.getHands().get("hand_l");

                       }
                       break;
                case 2:
                       break;
            }
        }

    } // end of handleHandsData

    private void handlePalmPosition(Hand hand, HandView handView){
        float x = hand.palmPosition().getX();
        float z = hand.palmPosition().getZ();

        handView.centerX().set(x);
        handView.centerY().set(z);
    }

    private void handleFingersPosition(Hand hand, HandView handView){
        List<FingerView> fingersView = handView.getFingers();
        int ctr = 0;
        for (Finger finger : hand.fingers()){
            float x = finger.tipPosition().getX();
            float z = finger.tipPosition().getZ();

            FingerView fingerView = fingersView.get(ctr);

            fingerView.centerX().set(x);
            fingerView.centerY().set(z);
            ctr++;
        }
    }


}
