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

        switch (hands.count()){
            case 1:
               Hand hand1 = hands.get(0);
               if(hand1.isRight()){
                   HandView handView = handsViewController.getHands().get(HandSide.HAND_R);
                   Platform.runLater(() -> {
                       handsViewController.addHandView(HandSide.HAND_R);
                       handsViewController.removeHandView(HandSide.HAND_L);
                   });

                   handlePalmPosition(hand1, handView);
                   handleFingersPosition(hand1, handView);

                   //System.out.println("Right Hand HANDLED");

               } else{
                   HandView handView = handsViewController.getHands().get(HandSide.HAND_L);
                   Platform.runLater(() -> {
                       handsViewController.addHandView(HandSide.HAND_L);
                       handsViewController.removeHandView(HandSide.HAND_R);
                   });

                   handlePalmPosition(hand1, handView);
                   handleFingersPosition(hand1, handView);
                   //System.out.println("Left Hand HANDLED");
               }
               break;
            case 2:
               //System.out.println("Handling both hands");
               int ctr = 0;
               HandView handView;
               for(Hand hand2 : hands){
                   switch (ctr){
                       case 0:
                           handView = handsViewController.getHands().get(HandSide.HAND_R);

                           Platform.runLater(() -> handsViewController.addHandView(HandSide.HAND_R));

                           handlePalmPosition(hand2, handView);
                           handleFingersPosition(hand2, handView);
                           //System.out.println("JavaFX palm position: " + handView.getLocationX() + " " + handView.getLocationY());

                           ctr++;
                           //System.out.println("Right Hand HANDLED");
                           break;
                       case 1:
                           handView = handsViewController.getHands().get(HandSide.HAND_L);
                           Platform.runLater(() -> handsViewController.addHandView(HandSide.HAND_L));

                           handlePalmPosition(hand2, handView);
                           handleFingersPosition(hand2, handView);

                           //System.out.println("Left Hand HANDLED");
                           break;
                   }
               }
               break;
            case 0:
                //System.out.println("No Hands in sensor vision");
                Platform.runLater(() -> {
                    handsViewController.removeHandView(HandSide.HAND_R);
                    handsViewController.removeHandView(HandSide.HAND_L);
                });

               break;
        }
    } // end of handleHandsData

    private void handlePalmPosition(Hand hand, HandView handView){
        Platform.runLater(() -> {
            float x = hand.palmPosition().getX();
            float z = hand.palmPosition().getZ();

           //System.out.println("Leap motion palm position: " + x + z);

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
