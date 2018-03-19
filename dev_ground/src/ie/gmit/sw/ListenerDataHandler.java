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
                       Hand hand1 = hands.get(0);
                       if(hand1.isRight()){
                           HandView handView = handsViewController.getHands().get("hand_r");

                           handlePalmPosition(hand1, handView);
                           handleFingersPosition(hand1, handView);

                       } else{
                           HandView handView = handsViewController.getHands().get("hand_l");
                           handlePalmPosition(hand1, handView);
                           handleFingersPosition(hand1, handView);
                       }
                       break;
                case 2:
                       int ctr = 0;
                       HandView handView;
                       for(Hand hand2 : hands){
                           switch (ctr){
                               case 0:
                                   handView = handsViewController.getHands().get("hand_r");
                                   handlePalmPosition(hand2, handView);
                                   handleFingersPosition(hand2, handView);
                                   ctr++;
                                   break;
                               case 1:
                                   handView = handsViewController.getHands().get("hand_l");
                                       handlePalmPosition(hand2, handView);
                                       handleFingersPosition(hand2, handView);
                                   break;
                           }
                       }
                       break;
            }
        }

    } // end of handleHandsData

    private void handlePalmPosition(Hand hand, HandView handView){
        Platform.runLater(() -> {
            float x = hand.palmPosition().getX();
            float z = hand.palmPosition().getZ();

            handView.centerX().set(x);
            handView.centerY().set(z);
        });
    }

    private void handleFingersPosition(Hand hand, HandView handView){
        Platform.runLater(() -> {
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
        });
    }


}
